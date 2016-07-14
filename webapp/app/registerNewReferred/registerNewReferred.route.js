(function() {
  'use strict';

  angular
    .module('refiereApp.registerNewReferred')
    .config(setupRoutes);

  function setupRoutes($stateProvider) {
    $stateProvider
      .state('registerNewReferred', {
          url: '/registerNewReferred',
          templateUrl: 'app/registerNewReferred/view.html',
          controller: 'RegisterNewReferredCtrl as vm'
        });
  }
})();
