angular.module('app.comprar', ['ngRoute', 'ngCookies'])

.config([ '$routeProvider',
    function($routeProvider) {
      $routeProvider.
    		when('/comprar/:vuelo', {
    			templateUrl: 'comprar/comprar.html',
    			controller: 'comprarCtrl'
    		});
    }
  ])
  
.controller('comprarCtrl', function($scope, $http, $cookies, $location){
	
})