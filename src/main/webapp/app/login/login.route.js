(function() {
  'use strict';

  angular
    .module('refiereApp.login')
    .config(setupRoutes);

  function setupRoutes($stateProvider) {
    $stateProvider
      .state('login', {
        url: '/login',
        templateUrl: 'app/login/view.html',
        controller: 'LoginCtrl as vm'
      });
  }
})();