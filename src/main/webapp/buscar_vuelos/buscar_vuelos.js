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
    function($scope, $http, $location){
  	  $scope.isCollapsed = true;
      $scope.cargando = false;
      $scope.listaAeropuertos = [];

      // Pide la lista de aeropuertos
      $http({
        method: 'GET',
        url: 'http://localhost:8080/Aerolinea/rest/aeropuertos/'
      }).then(function successCallback(response){
        $scope.aeropuertos = response.data;
        // Formatea la lista de aeropuertos
        for (var i = 0; i<$scope.aeropuertos.length; i++){
          $scope.listaAeropuertos[i] =
            '[' + $scope.aeropuertos[i].iata + ']  ' +
            $scope.aeropuertos[i].nombre + '.   ' +
            $scope.aeropuertos[i].ciudad.nombre + ', ' +
            $scope.aeropuertos[i].ciudad.pais.nombre + '.';
        }
      }, function errorCallback(response){

      });


      $scope.desde;
      $scope.hasta;
      // Crea una fecha inicial (actual) y le da formato
      $scope.datepicker =
        moment().format('YYYY-MM-DD') + "T" +
        moment().hours() + ":" +
        moment().minutes() + ":00Z";

      // Pide los vuelos con los datos proporcionados
      $scope.vuelos = [];
      $scope.buscarVuelos = function() {
        $scope.cargando = true;
        var urlCompleta = 'http://localhost:8080/Aerolinea/rest/vuelos/busqueda' +
          '?' +
          'desde=' + $scope.desde.substr(1,3) + '&' +
          'hasta=' + $scope.hasta.substr(1,3) + '&' +
          'salida=' + $scope.datepicker;
        $http({
          method: 'GET',
          url: urlCompleta
        }).then(function successCallback(response) {
          $scope.cargando = false;
          $scope.vuelos = response.data;
          $scope.isCollapsed = false;
          // Le da formato a las fechas que se devuelven
          for (var i = 0; i < $scope.vuelos.length; i++){
            $scope.vuelos[i].salida = moment($scope.vuelos[i].salida).format('DD-MM-YYYY / HH:mm');
            $scope.vuelos[i].llegada = moment($scope.vuelos[i].llegada).format('DD-MM-YYYY / HH:mm');
          }
          console.log($scope.vuelos)
        }, function errorCallback(response) {
            $scope.cargando = false;
        });
      };

      $scope.goComprar = function(id) {
    	  $location.path('/comprar/' + id);
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
