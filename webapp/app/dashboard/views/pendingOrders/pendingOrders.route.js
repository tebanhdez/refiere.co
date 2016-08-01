(function() {
  'use strict';

  angular
    .module('refiereApp.pendingOrders')
    .config(setupRoutes);

  function setupRoutes($stateProvider) {
    $stateProvider
      .state('pendingOrders', {
        url: '/pendingOrders',
        templateUrl: 'app/dashboard/views/pendingOrders/view.html',
        controller: 'OrdersCtrl as vm'
      });
  }
})();
