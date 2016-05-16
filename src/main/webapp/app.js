/**
 *
 */

var app = angular.module('app', [
	 'ngRoute',
	 'ngCookies',
	 //Agregar aca todos los controladores de las demas vistas aca
	 'app.registrosocio',
	 'app.ingresosocio'
	 ])

	 .config([ '$routeProvider',
	     function($routeProvider) {
	       $routeProvider.otherwise('/');
	     }
	   ])

	 
