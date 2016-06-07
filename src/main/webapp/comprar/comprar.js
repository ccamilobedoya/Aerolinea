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
          recorrido += $scope.vuelo.avion.filas;
        }
      }
      console.log($scope.sillas);
    }, function errorCallback(response) {});

  }, function errorCallback(response) {});

  // Click en una silla determinada
  $scope.clickSilla = function(id_silla) {
    alert(id_silla);
  };

  // Tipos de identificacion
  $scope.tipoDocumento = '';
  $scope.tiposDocumentos = ['Cedula', 'DNI'];

})
