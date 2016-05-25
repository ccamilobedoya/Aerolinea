/**
 *
 */

var app = angular.module('app', [
	 'ngRoute',
	 'ngCookies',
	 //Agregar aca todos los controladores de las demas vistas aca
	 'app.registrosocio',
	 'app.ingresosocio',
	 'app.principal'
	 ])

	 .config([ '$routeProvider',
	     function($routeProvider) {
	       $routeProvider.otherwise('/');
	     }
	   ])

app.controller('appCtrl', ['$scope', '$cookies', '$rootScope', function($scope, $cookies, $rootScope) {
	$rootScope.logueado = false;
	$rootScope.nombreUsuario = $cookies.get('user');
	if ($rootScope.nombreUsuario == null){
		$rootScope.logueado = false;
	}
	else {
		$rootScope.logueado = true;
	}

	$scope.salir = function() {
		$cookies.remove('user');
		$rootScope.logueado = false;
	};
}]);
