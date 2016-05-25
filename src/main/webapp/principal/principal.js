angular.module('app.principal', ['ngRoute'])

.config([ '$routeProvider',
    function($routeProvider) {
      $routeProvider.
    		when('/', {
    			templateUrl: 'principal/principal.html',
    			controller: 'principalCtrl'
    		});
    }
  ])

.controller('principalCtrl', function() {

});


function parallax(){
    var jumboHeight = $('.header-gigante').outerHeight();
    var scrolled = $(window).scrollTop();
    $('.header-bg').css('height', (jumboHeight-(scrolled)) + 'px');
}

$(window).scroll(function(e){
    parallax();
});
