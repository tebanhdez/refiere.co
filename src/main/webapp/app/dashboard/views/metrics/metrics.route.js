(function() {
  'use strict';

  angular
    .module('refiereApp.metrics')
    .config(setupRoutes);

  function setupRoutes($stateProvider) {
    $stateProvider
    .state('metrics', {
      url: '/metrics',
      templateUrl: 'app/dashboard/views/metrics/view.html',
      controller: 'MetricsCtrl as vm'
    });
  }
})();
