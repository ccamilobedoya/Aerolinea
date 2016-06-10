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


app.factory('correo', ['$http', function($http) {

	return function(nombre, correo, asunto, titulo, texto) {
		var textos = [nombre, correo, asunto, titulo, texto];
		var t = '<!DOCTYPE html "-//w3c//dtd xhtml 1.0 transitional //en" "http://www.w3.org/tr/xhtml1/dtd/xhtml1-transitional.dtd"><html xmlns="http://www.w3.org/1999/xhtml"><head> <meta http-equiv="Content-Type" content="text/html; charset=utf-8"> <meta name="viewport" content="width=device-width"> <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE"> <title>Template Base</title> <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet" type="text/css"> </head> <body style="width: 100% !important;min-width: 100%;-webkit-text-size-adjust: 100%;-ms-text-size-adjust: 100% !important;margin: 0;padding: 0;background-color: #FFFFFF"> <style id="media-query"> #outlook a { padding: 0; } .ExternalClass { width: 100%; } .ExternalClass, .ExternalClass p, .ExternalClass span, .ExternalClass font, .ExternalClass td, .ExternalClass div { line-height: 100%; } #backgroundTable { margin: 0; padding: 0; width: 100% !important; line-height: 100% !important; } .button a { display: inline-block; text-decoration: none; -webkit-text-size-adjust: none; text-align: center; } .button a div { text-align: center !important; } body.outlook p { display: inline !important; } @media only screen and (max-width: 500px) { table[class="body"] img { height: auto !important; width: 100% !important; } table[class="body"] img.fullwidth { max-width: 100% !important; } table[class="body"] center { min-width: 0 !important; } table[class="body"] .container { width: 95% !important; } table[class="body"] .row { width: 100% !important; display: block !important; } table[class="body"] .wrapper { display: block !important; padding-right: 0 !important; } table[class="body"] .columns, table[class="body"] .column { table-layout: fixed !important; float: none !important; width: 100% !important; padding-right: 0px !important; padding-left: 0px !important; display: block !important; } table[class="body"] .wrapper.first .columns, table[class="body"] .wrapper.first .column { display: table !important; } table[class="body"] table.columns td, table[class="body"] table.column td, .col { width: 100% !important; } table[class="body"] table.columns td.expander { width: 1px !important; } table[class="body"] .right-text-pad, table[class="body"] .text-pad-right { padding-left: 10px !important; } table[class="body"] .left-text-pad, table[class="body"] .text-pad-left { padding-right: 10px !important; } table[class="body"] .hide-for-small, table[class="body"] .show-for-desktop { display: none !important; } table[class="body"] .show-for-small, table[class="body"] .hide-for-desktop { display: inherit !important; } .mixed-two-up .col { width: 100% !important; } } @media screen and (max-width: 500px) { div[class="col"] { width: 100% !important; } } @media screen and (min-width: 501px) { table[class="container"] { width: 500px !important; } } </style> <table class="body" style="border-spacing: 0;border-collapse: collapse;vertical-align: top;height: 100%;width: 100%;table-layout: fixed" cellpadding="0" cellspacing="0" width="100%" border="0"> <tbody><tr style="vertical-align: top"> <td class="center" style="word-break: break-word;border-collapse: collapse !important;vertical-align: top;text-align: center;background-color: #FFFFFF" align="center" valign="top"> <table style="border-spacing: 0;border-collapse: collapse;vertical-align: top;background-color: #65947a" cellpadding="0" cellspacing="0" align="center" width="100%" border="0"> <tbody><tr style="vertical-align: top"> <td style="word-break: break-word;border-collapse: collapse !important;vertical-align: top" width="100%"> <table class="container" style="border-spacing: 0;border-collapse: collapse;vertical-align: top;max-width: 500px;margin: 0 auto;text-align: inherit" cellpadding="0" cellspacing="0" align="center" width="100%" border="0"><tbody><tr style="vertical-align: top"><td style="word-break: break-word;border-collapse: collapse !important;vertical-align: top" width="100%"><table class="block-grid" style="border-spacing: 0;border-collapse: collapse;vertical-align: top;width: 100%;max-width: 500px;color: #333;background-color: transparent" cellpadding="0" cellspacing="0" width="100%" bgcolor="transparent"><tbody><tr style="vertical-align: top"><td style="word-break: break-word;border-collapse: collapse !important;vertical-align: top;text-align: center;font-size: 0"><div class="col num12" style="display: inline-block;vertical-align: top;width: 100%"><table style="border-spacing: 0;border-collapse: collapse;vertical-align: top" cellpadding="0" cellspacing="0" align="center" width="100%" border="0"><tbody><tr style="vertical-align: top"><td style="word-break: break-word;border-collapse: collapse !important;vertical-align: top;background-color: transparent;padding-top: 20px;padding-right: 0px;padding-bottom: 30px;padding-left: 0px;border-top: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid #B6B6B6;border-left: 0px solid transparent"><table style="border-spacing: 0;border-collapse: collapse;vertical-align: top" cellpadding="0" cellspacing="0" width="100%"> <tbody><tr style="vertical-align: top"> <td style="word-break: break-word;border-collapse: collapse !important;vertical-align: top;padding-top: 10px;padding-right: 10px;padding-bottom: 0px;padding-left: 10px"> <div style="color:#555555;line-height:120%;font-family:"Open Sans", "Helvetica Neue", Helvetica, Arial, sans-serif;"> <div style="font-size:12px;line-height:14px;font-family:"Open Sans", "Helvetica Neue", Helvetica, Arial, sans-serif;color:#555555;text-align:left;"><p style="margin: 0;font-size: 18px;line-height: 22px;text-align: center"><span style="font-size: 36px; line-height: 43px; color: rgb(255, 255, 255);"><span style="line-height: 43px; font-size: 36px;">&#65279;Aerolinea UdeA<span style="line-height: 43px; font-size: 36px;">&#65279;</span></span></span></p></div> </div> </td> </tr> </tbody></table> </td></tr></tbody></table></div>></td></tr></tbody></table></td></tr></tbody></table> </td> </tr> </tbody></table> <table style="border-spacing: 0;border-collapse: collapse;vertical-align: top;background-color: transparent" cellpadding="0" cellspacing="0" align="center" width="100%" border="0"> <tbody><tr style="vertical-align: top"> <td style="word-break: break-word;border-collapse: collapse !important;vertical-align: top" width="100%"> <table class="container" style="border-spacing: 0;border-collapse: collapse;vertical-align: top;max-width: 500px;margin: 0 auto;text-align: inherit" cellpadding="0" cellspacing="0" align="center" width="100%" border="0"><tbody><tr style="vertical-align: top"><td style="word-break: break-word;border-collapse: collapse !important;vertical-align: top" width="100%"><table class="block-grid" style="border-spacing: 0;border-collapse: collapse;vertical-align: top;width: 100%;max-width: 500px;color: #333;background-color: transparent" cellpadding="0" cellspacing="0" width="100%" bgcolor="transparent"><tbody><tr style="vertical-align: top"><td style="word-break: break-word;border-collapse: collapse !important;vertical-align: top;text-align: center;font-size: 0"><div class="col num12" style="display: inline-block;vertical-align: top;width: 100%"><table style="border-spacing: 0;border-collapse: collapse;vertical-align: top" cellpadding="0" cellspacing="0" align="center" width="100%" border="0"><tbody><tr style="vertical-align: top"><td style="word-break: break-word;border-collapse: collapse !important;vertical-align: top;background-color: transparent;padding-top: 30px;padding-right: 0px;padding-bottom: 30px;padding-left: 0px;border-top: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-left: 0px solid transparent"><table style="border-spacing: 0;border-collapse: collapse;vertical-align: top" cellpadding="0" cellspacing="0" width="100%"> <tbody><tr style="vertical-align: top"> <td style="word-break: break-word;border-collapse: collapse !important;vertical-align: top;padding-top: 10px;padding-right: 10px;padding-bottom: 0px;padding-left: 10px"> <div style="color:#555555;line-height:120%;font-family:Arial, "Helvetica Neue", Helvetica, sans-serif;"> <div style="font-size:12px;line-height:14px;color:#555555;font-family:Arial, "Helvetica Neue", Helvetica, sans-serif;text-align:left;"><p style="margin: 0;font-size: 14px;line-height: 17px"><span style="font-size: 24px; line-height: 28px;" mce-data-marked="1"><strong><span style="line-height: 28px; font-size: 24px;" mce-data-marked="1">&#161;' + textos[3]+ '</span></strong></span></p></div> </div> </td> </tr> </tbody></table> <table style="border-spacing: 0;border-collapse: collapse;vertical-align: top" cellpadding="0" cellspacing="0" width="100%"> <tbody><tr style="vertical-align: top"> <td style="word-break: break-word;border-collapse: collapse !important;vertical-align: top;padding-top: 5px;padding-right: 10px;padding-bottom: 5px;padding-left: 10px"> <div style="color:#777777;line-height:120%;font-family:Arial, "Helvetica Neue", Helvetica, sans-serif;"> <div style="font-size:12px;line-height:14px;color:#777777;font-family:Arial, "Helvetica Neue", Helvetica, sans-serif;text-align:left;"><p style="margin: 0;font-size: 14px;line-height: 17px"><span style="font-size: 16px; line-height: 19px;" mce-data-marked="1">' + textos[4] + '</span></p></div> </div> </td> </tr> </tbody></table> <table style="border-spacing: 0;border-collapse: collapse;vertical-align: top" cellpadding="0" cellspacing="0" width="100%"> <tbody><tr style="vertical-align: top"> <td style="word-break: break-word;border-collapse: collapse !important;vertical-align: top;padding-top: 15px;padding-right: 10px;padding-bottom: 10px;padding-left: 10px"> <div style="color:#aaaaaa;line-height:120%;font-family:Arial, "Helvetica Neue", Helvetica, sans-serif;"> <div style="font-size:12px;line-height:14px;color:#aaaaaa;font-family:Arial, "Helvetica Neue", Helvetica, sans-serif;text-align:left;"><p style="margin: 0;font-size: 14px;line-height: 17px">Si no has hecho esto tu, contacta con servicio al cliente inmediatamente.<br></p></div> </div> </td> </tr> </tbody></table> </td></tr></tbody></table></div></td></tr></tbody></table></td></tr></tbody></table> </td> </tr> </tbody></table> </td> </tr> </tbody></table> </body></html>';
		var data = [nombre, correo, asunto, t];
		$http.post('http://localhost:8080/Aerolinea/rest/correo/', data);
	};
 }]);





/*
*/
