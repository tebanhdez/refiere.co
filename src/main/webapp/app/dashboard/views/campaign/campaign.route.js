(function() {
  'use strict';

  angular
    .module('refiereApp.dashboard.views.campaign')
    .config(setupRoutes);

  function setupRoutes($stateProvider) {
    $stateProvider
    .state('campaign', {
      url: '/campaign',
      templateUrl: 'app/dashboard/views/campaign/viewAll.html',
    });
  }
})();