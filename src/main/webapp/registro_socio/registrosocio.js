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
      $scope.mensaje = [alerta = '', titulo = '', texto = ''];
      $scope.isCollapsed = true;
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
          "http://localhost:8080/Aerolinea/rest/socio/registrar",
          datos).then(function successCallback (response){
          	  $scope.mensaje.alerta = 'alert-success';
              $scope.mensaje.titulo = '¡Registrado! ';
              $scope.mensaje.texto = 'Ahora puedes ingresar como socio.'
              $scope.isCollapsed = false;
              //window.location.href = '/#';
        	  }
          , function errorCallback (response) {
              $scope.mensaje.alerta = 'alert-warning';
              $scope.mensaje.titulo = '¡Informacion incorrecta! ';
              $scope.mensaje.texto = 'Verifica que la informacion introducida sea correcta, o quizas no seas un cliente activo.';
              $scope.isCollapsed = false;
            }
          );
      };
      $scope.tiposDocumentos = ['Cedula', 'DNI'];
    }
  )
