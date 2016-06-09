angular.module('app.usuario', ['ngRoute', 'ngCookies'])

.config([ '$routeProvider',
    function($routeProvider) {
      $routeProvider.
    		when('/usuario', {
    			templateUrl: 'usuario/usuario.html',
    			controller: 'usuarioCtrl'
    		});
    }
  ])

.controller('usuarioCtrl', function($scope, $http, $cookies) {
  // mensaje
  $scope.mensaje = [];
  $scope.mensaje.on = false;

  // Muestra la TAB seleccionada
  $scope.t1 = true;
  $scope.t2 = false;
  $scope.t3 = false;
  $scope.tabclick = function (tab){
    switch (tab) {
      case '1':
        $scope.t1 = true;
        $scope.t2 = false;
        $scope.t3 = false;
        break;
      case '2':
        $scope.t2 = true;
        $scope.t1 = false;
        $scope.t3 = false;
        break;
      case '3':
        $scope.t3 = true;
        $scope.t2 = false;
        $scope.t1 = false;
        break;
      default:
    }
  };

  $scope.usuario = [];
  $http.get('http://localhost:8080/Aerolinea/rest/socio/consultar' +
    '?usuario=' + $cookies.get('user')
  ).then(function successCallback(response) {
    $scope.usuario = response.data;
  });

  // Trae todos los pasajes del usuario
  $scope.pasaje = [];
  $http.get('http://localhost:8080/Aerolinea/rest/pasaje/consultar' +
    '?usuario=' + $cookies.get('user')
  ).then(function successCallback(response) {
    $scope.pasaje = response.data;
  });

  // Cambiar contraseña
  $scope.contrasena = '';
  $scope.contrasenan = '';
  $scope.cambiar = function(){
    var datos = [$cookies.get('user'), $scope.contrasena, $scope.contrasenan];
    $http.post('http://localhost:8080/Aerolinea/rest/socio/editarcontrasena', datos)
    .then(function successCallback(response) {
      $scope.mensaje.alerta = 'alert-info';
      $scope.mensaje.titulo = '¡Perfecto! ';
      $scope.mensaje.texto = 'Tu contraseña se cambio correctamente.';
      $scope.mensaje.on = true;
    }, function errorCallback(response) {
      $scope.mensaje.alerta = 'alert-warning';
      $scope.mensaje.titulo = '¡Algo ocurrio! ';
      $scope.mensaje.texto = 'Probablemente tu contraseña este mal escrita.';
      $scope.mensaje.on = true;
    });
  };

})
