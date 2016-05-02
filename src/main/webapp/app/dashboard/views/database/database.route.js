(function() {
  'use strict';

  angular
    .module('refiereApp.dashboard.views.database')
    .config(setupRoutes);

  function setupRoutes($stateProvider) {
    $stateProvider
    .state('database', {
      url: '/database',
      templateUrl: 'app/dashboard/views/database/viewAll.html',
    });
  }
})();