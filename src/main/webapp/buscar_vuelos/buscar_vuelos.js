angular.module('app.buscarvuelos', ['ngRoute'])

.config([ '$routeProvider',
    function($routeProvider) {
      $routeProvider.
    		when('/buscar', {
    			templateUrl: 'buscar_vuelos/buscar_vuelos.html',
    			controller: 'buscarVuelosCtrl'
    		});
    }
  ])

  .controller('buscarVuelosCtrl',
    function($scope, $http){
      $scope.listaAeropuertos = ['BOG', 'OLH', 'JFK'];

      $scope.desde;
      $scope.hasta;
      $scope.datepicker =
        moment().format('YYYY-MM-DD') + "T" +
        moment().hours() + ":" +
        moment().minutes() + ":00Z";

      $scope.vuelos = [];
      $scope.buscarVuelos = function() {
        var urlCompleta = 'http://localhost:8080/Aerolinea/rest/vuelos/busqueda' +
          '?' +
          'desde=' + $scope.desde + '&' +
          'hasta=' + $scope.hasta + '&' +
          'salida=' + $scope.datepicker;
        $http({
          method: 'GET',
          url: urlCompleta
        }).then(function successCallback(response) {
          $scope.vuelos = response;
        }, function errorCallback(response) {

        });
      };

      // Crea el date picker
      $('input[name="datepicker"]').daterangepicker({
          "singleDatePicker": true,
          "minDate": moment(),
          "startDate": moment(),
          "opens": "center",
          "drops": "up"
      }, function(start, end, label) {
        $scope.datepicker = start.format('YYYY-MM-DDT') + moment().hours() + ":" + moment().minutes() + ":00Z";
      });
    })
