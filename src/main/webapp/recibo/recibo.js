angular.module('app.recibo', ['ngRoute', 'ngCookies'])

.config([ '$routeProvider',
    function($routeProvider) {
      $routeProvider.
    		when('/recibo/:documento?/:tipo?/:nombre?/:correo?/:pago?/:precio?/:pasaje?/:fila?/:columna?/:avion?/:horasalida?/:horallegada?/:lugarsalida?/:lugarllegada?/:dirsalida?/:dirllegada?/:telsalida?/:telllegada?', {
    			templateUrl: 'recibo/recibo.html',
    			controller: 'reciboCtrl'
    		});
    }
  ])

.controller('reciboCtrl', function($scope, $http, $routeParams, $location){
  // Guarda los datos que entraron como parametros
  $scope.base = $routeParams;

  // Genera el codigo de barras de acuerdo al id de pasaje
  JsBarcode("#barcode", $scope.base.pasaje, {
    height: 40,
    displayValue: false
  });

  // Funcion para imprimir (Se le manda el id del div que tiene lo que se imprime)
  $scope.print = function(printSectionId) {
      var innerContents = document.getElementById(printSectionId).innerHTML;
      var popupWinindow = window.open('', '_blank', 'width=600,height=700,scrollbars=no,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
      popupWinindow.document.open();
      // Agregar los css correspondientes
      var headcontents = '<link rel="stylesheet" href="resources/css/bootstrap.css"><link rel="stylesheet" href="resources/css/daterangepicker.css"><link rel="stylesheet" href="resources/css/ring.css"><link rel="stylesheet" href="resources/css/paymentfont.css"><link rel="stylesheet" href="resources/css/app.css"><link href="https://fonts.googleapis.com/css?family=Open+Sans:400,300,600" rel="stylesheet" type="text/css">';
      popupWinindow.document.write('<html><head>' + headcontents + '</head><body onload="window.print()">' + innerContents + '</html>');
      popupWinindow.document.close();
    }


})
