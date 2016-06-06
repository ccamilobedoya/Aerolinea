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
      $scope.sillas = response.data;
      console.log($scope.sillas);
    }, function errorCallback(response) {});

  }, function errorCallback(response) {});




  // Tipos de identificacion
  $scope.tipoDocumento = '';
  $scope.tiposDocumentos = ['Cedula', 'DNI'];

})
