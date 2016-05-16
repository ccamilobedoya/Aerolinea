angular.module('app.ingresosocio', ['ngRoute', 'ngCookies'])

.config([ '$routeProvider',
    function($routeProvider) {
      $routeProvider.
    		when('/ingreso', {
    			templateUrl: 'ingreso_socio/ingresosocio.html',
    			controller: 'ingresoSocioCtrl'
    		});
    }
  ])

.controller('ingresoSocioCtrl', function($scope, $http, $cookies){
  $scope.mensaje = [alerta = '', titulo = '', texto = ''];
  $scope.isCollapsed = true;
  $scope.usuario = '';
  $scope.contrasena = '';
  $scope.ingresar = function () {
    var datos = JSON.stringify(
      {
        "usuario" : $scope.usuario,
        "contrasena" : $scope.contrasena
      }
    );
    $http.post(
      'http://localhost:8080/Aerolinea/rest/socio/ingresar', datos)
      .then(
        function successCallback (response) {
          $scope.mensaje.alerta = 'alert-success';
          $scope.mensaje.titulo = '¡Bienvenido! ';
          $scope.mensaje.texto = '';
          $scope.isCollapsed = false;
          $cookies.put ('user', $scope.usuario);
        },
        function errorCallback (response) {
          $scope.mensaje.alerta = 'alert-warning';
          $scope.mensaje.titulo = '¡Informacion incorrecta! ';
          $scope.mensaje.texto = 'Por favor verifica todos tus datos';
          $scope.isCollapsed = false;
        }
      );

  };
})
