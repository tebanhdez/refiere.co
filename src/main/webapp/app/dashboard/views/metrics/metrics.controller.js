(function() {
  'use strict';

  angular
    .module('refiereApp.dashboard')
    .controller('MetricsCtrl', MetricsCtrl);

    MetricsCtrl.$inject = ['$scope'];

  function MetricsCtrl($scope) {
    var vm = this;
  }
})();

