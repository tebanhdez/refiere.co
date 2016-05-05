(function() {
  'use strict';

  angular
    .module('refiereApp.dashboard')
    .config(setupRoutes);

  function setupRoutes($stateProvider) {
    $stateProvider
      .state('dashboard', {
        url: '/dashboard',
        templateUrl: 'app/dashboard/view.html',
        controller: 'DashboardCtrl'
      })
      .state('dashboard.campaign', {
        url: '/campaign',
        templateUrl: 'app/dashboard/views/campaign/view.html',
      })
      .state('dashboard.database', {
        url: '/database',
        templateUrl:'app/dashboard/views/database/view.html'
      });
  }
})();
