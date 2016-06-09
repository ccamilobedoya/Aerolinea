angular.module('app.recibo', ['ngRoute', 'ngCookies'])

.config([ '$routeProvider',
    function($routeProvider) {
      $routeProvider.
    		when('/recibo/:documento?/:tipo?/:nombre?/:correo?/:pago?/:precio?/:pasaje?/:fila?/:columna?/:avion?/:horasalida?/:horallegada?/:lugarsalida?/:lugarllegada?/:dirsalida?/:dirllegada?/:telsalida?/:telllegada?', {
    			templateUrl: 'recibo/recibo.html',
    			controller: 'reciboCtrl'
    		});
    }
  ])

.controller('reciboCtrl', function($scope, $http, $routeParams, $location){
  $scope.base = $routeParams;
  console.log($scope.base);
})
