(function() {
  'use strict';

  angular
    .module('refiereApp.dashboard')
    .controller('MetricsCtrl', MetricsCtrl);

    MetricsCtrl.$inject = ['jkuri.datepicker'];

  function MetricsCtrl(ngDatepicker) {
    var vm = this;
  }
})();
