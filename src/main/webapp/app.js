/**
 *
 */

var app = angular.module('app', [
	 'ngRoute',
	 'ngCookies',
	 //Agregar aca todos los controladores de las demas vistas aca
	 'app.registrosocio',
	 'app.ingresosocio',
	 'app.principal',
	 'app.buscarvuelos',
	 'app.comprar',
	 'app.recibo',
	 'app.usuario'
	 ])

	 .config([ '$routeProvider',
	     function($routeProvider) {
	       $routeProvider.otherwise('/');
	     }
	   ])

app.controller('appCtrl', ['$scope', '$cookies', '$rootScope', '$location', function($scope, $cookies, $rootScope, $location) {
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
		$location.path('/');
	};
}]);

// Carga entre views
app.run(['$rootScope',function($rootScope){

    $rootScope.isLoading = false;
    $rootScope.$on('$routeChangeStart', function() {
        $rootScope.isLoading = true;
    });
    $rootScope.$on('$routeChangeSuccess', function() {
        $rootScope.isLoading = false;
    });
    $rootScope.$on('$routeChangeError', function() {

    });

}]);

/*
app.directive('toggle', function(){
  return {
    restrict: 'A',
    link: function(scope, element, attrs){
      if (attrs.toggle=="tooltip"){
        $(element).tooltip();
      }
      if (attrs.toggle=="popover"){
        $(element).popover();
      }
    }
  };
});
*/
