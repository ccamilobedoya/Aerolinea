/**
 *
 */

angular.module('app.registrosocio', ['ngRoute'])

.config([ '$routeProvider',
    function($routeProvider) {
      $routeProvider.
    		when('/registro', {
    			templateUrl: 'registro_socio/registrosocio.html',
    			controller: 'registroSocioCtrl'
    		});
    }
  ])

.controller('registroSocioCtrl',
		function ($scope, $http){
			$scope.documento = '';
			$scope.usuario = '';
			$scope.contrasena = '';
      $scope.tipoDocumento = '';
      $scope.hallarTipoDocumento = function (item) {
        $scope.tipoDocumento = item;
      };
			$scope.registrar = function() {
				var datos = JSON.stringify(
					{
					    "cliente" : {
					        "documento" : $scope.documento,
					        "tipoDocumento" : {
					            "nombre" : $scope.tipoDocumento
					        }
					    },
					    "usuario" : $scope.usuario,
					    "contrasena" : $scope.contrasena
					}
				);
        $http.post(
          "http://localhost:8080/Aerolinea/rest/socio",
          datos).success(function(){
        	   alert("Socio creado");
        	  }
          );
      };
      $scope.tiposDocumentos = ['Cedula', 'DNI'];
    }
  )
