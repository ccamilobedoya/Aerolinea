angular.module('app.comprar', ['ngRoute', 'ngCookies'])

.config([ '$routeProvider',
    function($routeProvider) {
      $routeProvider.
    		when('/comprar/:vueloid', {
    			templateUrl: 'comprar/comprar.html',
    			controller: 'comprarCtrl'
    		});
    }
  ])

.controller('comprarCtrl', function($scope, $http, $cookies, $location, $routeParams){
  // Para los mensajes
  $scope.isCollapsed = true;
  $scope.mensaje = [];

  // Datos del formulario
  $scope.nombre = '';
  $scope.documento = '';
  $scope.correo = '';
  $scope.tipoDocumento = '';
  $scope.millas = '';
  $scope.readonly = false;
  $scope.isnUser = true;
  $scope.checkedPagar = true;
  // Tipos de identificacion
  $scope.tiposDocumentos = ['Cedula', 'DNI'];

  // Si ya es un socio
  $scope.socio = [];
  var user = $cookies.get('user');
  if (user != null) {
    // si hay un socio logueado, busca sus datos
    $http.get('http://localhost:8080/Aerolinea/rest/socio/consultar'
      + '?usuario=' + user).then(
        function successCallback(response){
          $scope.socio = response.data;

          // Cambia los datos pertinentes de acuerdo a los del socio
          $scope.nombre = $scope.socio.cliente.nombre;
          $scope.documento = $scope.socio.cliente.documento;
          $scope.correo = $scope.socio.cliente.correo;
          $scope.tipoDocumento = $scope.socio.cliente.tipoDocumento.nombre;
          $scope.millas = $scope.socio.millas;
          $scope.readonly = true;
          $scope.isnUser = false;
        }
    );
  }

  // Busca los datos del vuelo de acuerdo a la url
  $scope.vuelo = '';
  $scope.sillas = [];
  var urlCompleta = 'http://localhost:8080/Aerolinea/rest/vuelos/busquedaunica' +
    '?' + 'id=' + $routeParams.vueloid;
  $http({
    method: 'GET',
    url: urlCompleta
  }).then(function successCallback(response) {
    $scope.vuelo = response.data;
    $scope.vuelo.salida = moment($scope.vuelo.salida).format('DD-MM-YYYY / HH:mm');
    $scope.vuelo.llegada = moment($scope.vuelo.llegada).format('DD-MM-YYYY / HH:mm');
    console.log($scope.vuelo);

    // Busca los datos de las sillas con respecto al vuelo anterior
    $http({
      method: 'GET',
      url: 'http://localhost:8080/Aerolinea/rest/sillas/listar'
        + '?vuelo=' + $scope.vuelo.id_vuelo
    }).then(function successCallback(response) {

      // Guarda en una matriz todas las sillas (columnas x filas)
      var recorrido = 0;
      for (var i = 0; i < $scope.vuelo.avion.filas; i++){
        $scope.sillas.push([]);
        recorrido = i;
        for (var j = 0; j < $scope.vuelo.avion.columnas; j++){
          $scope.sillas[i][j] = response.data[recorrido];
          if ($scope.sillas[i][j].pasaje != null){
            $scope.sillas[i][j].pasaje = 'ocupado';
          }
          else{
            $scope.sillas[i][j].pasaje = 'libre';
          }
          recorrido += $scope.vuelo.avion.filas;
        }
      }
      console.log($scope.sillas);
    }, function errorCallback(response) {});

  }, function errorCallback(response) {});

  // Click en una silla determinada para seleccionarla
  $scope.sillaSeleccionada = [];
  $scope.sillaSeleccionada.fila = '-';
  $scope.sillaSeleccionada.columna = '-';
  $scope.clickSilla = function(id_silla, f, c) {
    // Si la silla no esta ocupada (Es clickeable)
    if ($scope.sillas[f-1][c-1].pasaje != 'ocupado'){
      // Borra el color de la silla seleccionada anteriormente
      if ($scope.sillaSeleccionada.fila != '-'){
        $scope.sillas
        [$scope.sillaSeleccionada.fila-1]
        [$scope.sillaSeleccionada.columna-1].pasaje = 'libre';
      }
      // Guarda la silla seleccionada y le cambia el color
      $scope.sillaSeleccionada = $scope.sillas[f-1][c-1];
      $scope.sillas[f-1][c-1].pasaje = 'seleccionado';
    }
  };

  // Metodos de pagado
  $scope.otrosMetodos = function(){
    $scope.checkedPagar = true;
  }
  $scope.pagarMillas = function(){
    $scope.checkedPagar = false;
  }

  ///////////// Click en comprar //////////////
  $scope.comprar = function () {
    // Si aun no selecciona una silla o llena un dato
    if ($scope.sillaSeleccionada.fila == '-' ||
        !$scope.documento || !$scope.tipoDocumento ||
        !$scope.nombre || !$scope.correo){
      $scope.mensaje.alerta = 'alert-warning';
      $scope.mensaje.titulo = 'Problema con la informacion proporcionada ';
      $scope.mensaje.texto = 'Por favor verifica que toda la informacion sea correcta y que seleccionaras un puesto disponible.';
      $scope.isCollapsed = false;
    }
    // Si todos los datos estan
    else {
      // JSONs a usar
      var clientejson = JSON.stringify(
        {
          "documento" : $scope.documento,
          "tipoDocumento" : {
              "nombre" : $scope.tipoDocumento
          },
          "nombre" : $scope.nombre,
          "correo" : $scope.correo
        }
      );
      var pasajejson = JSON.stringify(
        {
          "cliente" : {
              "documento" : $scope.documento,
              "tipoDocumento" : {
                  "nombre" : $scope.tipoDocumento
              }
          },
          "itinerario" : {
              "id_itinerario" : $scope.vuelo.itinerario.id_itinerario
          },
          "pagado" : $scope.checkedPagar
        }
      );

      // Guardar el cliente
      $http.post('http://localhost:8080/Aerolinea/rest/cliente/guardar',
        clientejson).then(
          function successCallback(response){

            // Guardar Pasaje
            $http.post('http://localhost:8080/Aerolinea/rest/pasaje/guardar',
              pasajejson).then(
                function successCallback(response){

                  // Busca el id de pasaje
                  $scope.pasaje = '';
                  $http.get('http://localhost:8080/Aerolinea/rest/pasaje/consultarconitinerario'
                    + '?documento=' + $scope.documento
                    + '&tipo=' + $scope.tipoDocumento
                    + '&itinerario=' + $scope.vuelo.itinerario.id_itinerario)
                    .then(
                      function successCallback(response){
                        $scope.pasaje = response.data;

                        // Crea el JSON de silla aca ya que necesita el id del pasaje
                        var sillajson = JSON.stringify(
                          {
                            "id_silla" : $scope.sillaSeleccionada.id_silla,
                            "pasaje" : {
                                "id_pasaje" : $scope.pasaje.id_pasaje
                            }
                          }
                        );

                        // Actualizar silla
                        $http.post('http://localhost:8080/Aerolinea/rest/sillas/editar',
                          sillajson).then(
                            function successCallback(response){
                              $scope.mensaje.alerta = 'alert-info';
                              $scope.mensaje.titulo = '¡Gracias por su compra! ';
                              $scope.mensaje.texto = 'Seras redireccionado en unos segundos.';
                              $scope.isCollapsed = false;
                            },
                            function errorCallback(response){});
                    },function errorCallback(response){});
                },
                function errorCallback(response){});
          },
          function errorCallback(response){});
    }



  };


})
