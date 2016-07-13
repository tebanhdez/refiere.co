(function() {
  'use strict';

  angular
    .module('refiereApp.dashboard')
    .config(setupRoutes);

  function setupRoutes($stateProvider) {
    $stateProvider
      .state('sidebar', {
        url: '/sidebar',
        templateUrl: 'app/directives/sidebar/view.html',
        controller: 'SidebarCtrl as vm',
        bindToController: true
      });
  }
})();
