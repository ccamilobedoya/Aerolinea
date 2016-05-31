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

      $scope.datepicker =
        moment().format('YYYY-MM-DD') + "T" +
        moment().hours() + ":" +
        moment().minutes() + ":00Z";

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
