(function() {
  'use strict';

  angular
    .module('refiereApp.dashboard')
    .config(setupRoutes);

  function setupRoutes($stateProvider) {
    $stateProvider
      .state('app', {
        url: '/app',
        abstract : true,
        template : '<div ui-view></div>'
      })
      .state('app.dashboard', {
      url: '/dashboard',
      views: {
        '': {
          templateUrl: 'app/dashboard/view.html'
        },
        'header@app.dashboard': {
          templateUrl: 'app/dashboard/header/view.html'
        },
        'sidebar@app.dashboard': {
          templateUrl: 'app/dashboard/sidebar/view.html',
          controller: 'SidebarCtrl as sidebar'
        },
        'content@app.dashboard': {
          templateUrl: 'app/dashboard/views/campaign/view.html'
        }
      }
    });
  }
})();