(function() {
  'use strict';

  angular
    .module('refiereApp.dashboard')
    .controller('DashboardCtrl', DashboardCtrl);

  DashboardCtrl.$inject = ['$scope'];

  function DashboardCtrl($scope) {

    $scope.selectedOption ="database";
    $scope.tabs = [{
      title: 'Campaña',
      state: 'dashboard.campaign',
      icon: 'fa-dashboard'
    },{
      title: 'Métricas',
      state: 'dashboard.metrics',
      icon: 'fa-dashboard'
    },{
      title: 'Base de datos',
      state: 'dashboard.database',
      icon: 'fa-dashboard'
    },{
      title: 'Noticias',
      state: 'dashboard.news',
      icon: 'fa-book'
    },{
      title: 'Reportes',
      state: 'dashboard.reports',
      icon: 'fa-cogs'
    }];
  }
})();
