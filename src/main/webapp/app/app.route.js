(function() {
  'use strict';

  angular
    .module('refiereApp')
    .config(setRoutes);

  function setRoutes($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/');

    $stateProvider
      .state('home', {
        url: '/',
        templateUrl: 'app/home/view.html'
      });
  }
})();