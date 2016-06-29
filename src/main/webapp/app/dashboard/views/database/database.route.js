(function() {
  'use strict';

  angular
    .module('refiereApp.database')
    .config(setupRoutes);

  function setupRoutes($stateProvider) {
    $stateProvider
      .state('database', {
        url: '/database',
        templateUrl: 'app/dashboard/views/database/view.html',
        controller: 'DatabaseCtrl as vm'
      });
  }
})();
