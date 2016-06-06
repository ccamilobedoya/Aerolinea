angular.module('app.buscarvuelos', ['ngRoute'])

.config([ '$routeProvider',
    function($routeProvider) {
      $routeProvider.
    		when('/buscar', {
    			templateUrl: 'buscar_vuelos/buscar_vuelos.html',
    			controller: 'buscarVuelosCtrl'
    		});
    }
  ])

  .controller('buscarVuelosCtrl',
    function($scope, $http, $location){
  	  $scope.isCollapsed = true;
      $scope.cargando = false;
      $scope.listaAeropuertos = [];

      // Pide la lista de aeropuertos
      $http({
        method: 'GET',
        url: 'http://localhost:8080/Aerolinea/rest/aeropuertos/'
      }).then(function successCallback(response){
        $scope.aeropuertos = response.data;
        // Formatea la lista de aeropuertos
        for (var i = 0; i<$scope.aeropuertos.length; i++){
          $scope.listaAeropuertos[i] =
            '[' + $scope.aeropuertos[i].iata + ']  ' +
            $scope.aeropuertos[i].nombre + '.   ' +
            $scope.aeropuertos[i].ciudad.nombre + ', ' +
            $scope.aeropuertos[i].ciudad.pais.nombre + '.';
        }
      }, function errorCallback(response){

      });


      $scope.desde;
      $scope.hasta;
      // Crea una fecha inicial (actual) y le da formato
      $scope.datepicker =
        moment().format('YYYY-MM-DD') + "T" +
        moment().hours() + ":" +
        moment().minutes() + ":00Z";

      // Pide los vuelos con los datos proporcionados
      $scope.vuelos = [];
      $scope.buscarVuelos = function() {
        $scope.cargando = true;
        var urlCompleta = 'http://localhost:8080/Aerolinea/rest/vuelos/busqueda' +
          '?' +
          'desde=' + $scope.desde.substr(1,3) + '&' +
          'hasta=' + $scope.hasta.substr(1,3) + '&' +
          'salida=' + $scope.datepicker;
        $http({
          method: 'GET',
          url: urlCompleta
        }).then(function successCallback(response) {
          $scope.vuelos = response.data;

          // Para cada vuelo
          for (var i = 0; i < $scope.vuelos.length; i++){
            // Le da formato a las fechas que se devuelven
            $scope.vuelos[i].salida = moment($scope.vuelos[i].salida).format('DD-MM-YYYY / HH:mm');
            $scope.vuelos[i].llegada = moment($scope.vuelos[i].llegada).format('DD-MM-YYYY / HH:mm');
            // Busca sillas para cada vuelo
            buscarSillas(i);
          }

          $scope.cargando = false;
          $scope.isCollapsed = false;
          console.log($scope.vuelos)
        }, function errorCallback(response) {
            $scope.cargando = false;
        });
      };

      // Funcion para buscar las sillas vacias para cada vuelo y lo agrega
      function buscarSillas(j) {
        $http({
          method: 'GET',
          url: 'http://localhost:8080/Aerolinea/rest/sillas/contar'
            + '?vuelo=' + $scope.vuelos[j].id_vuelo
        }).then(function successCallback(response) {
                  $scope.vuelos[j].sillas = response.data;
                },
                function errorCallback(response){});
      }



      // Cada vuelo tiene este metodo
      $scope.goComprar = function(id) {
    	  $location.path('/comprar/' + id);
      };

      // Crea el date picker
      $('input[name="datepicker"]').daterangepicker({
          "singleDatePicker": true,
          "minDate": moment(),
          "startDate": moment(),
          "opens": "center",
          "drops": "up"
      }, function(start, end, label) {
        $scope.datepicker = start.format('YYYY-MM-DDT') + moment().hours() + ":" + moment().minutes() + ":00Z";
      });
    })
