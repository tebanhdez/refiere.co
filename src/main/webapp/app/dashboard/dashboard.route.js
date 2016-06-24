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
        controller: 'CampaignCtrl as vm'
      })
      .state('dashboard.metrics', {
        url: '/metrics',
        templateUrl: 'app/dashboard/views/metrics/view.html',
        controller: 'MetricsCtrl as vm'
      })
      .state('dashboard.database', {
        url: '/database',
        templateUrl:'app/dashboard/views/database/view.html',
      })
      .state('dashboard.news', {
        url: '/news',
        templateUrl: 'app/dashboard/views/news/view.html',
      })
      .state('dashboard.reports', {
        url: '/reports',
        templateUrl: 'app/dashboard/views/reports/view.html',
      })
  }
})();
