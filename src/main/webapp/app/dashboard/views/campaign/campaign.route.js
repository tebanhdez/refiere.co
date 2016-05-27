(function() {
  'use strict';

  angular
    .module('refiereApp.campaign')
    .config(setupRoutes);

  function setupRoutes($stateProvider) {
    $stateProvider
      .state('campaign', {
        url: '/campaign',
        templateUrl: 'app/dashboard/views/campaign/view.html',
        controller: 'CampaignCtrl as vm'
      });
  }
})();
