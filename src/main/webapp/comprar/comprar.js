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
  var urlCompleta = 'http://localhost:8080/Aerolinea/rest/vuelos/busquedaunica' +
    '?' + 'id=' + $routeParams.vueloid;
  $http({
    method: 'GET',
    url: urlCompleta
  }).then(function successCallback(response) {
    $scope.vuelo = response.data;
    console.log($scope.vuelo);
  }, function errorCallback(response) {

  });

  // Tipos de identificacion
  $scope.tipoDocumento = '';
  $scope.tiposDocumentos = ['Cedula', 'DNI'];

})
