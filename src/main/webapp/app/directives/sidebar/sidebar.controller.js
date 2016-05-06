(function() {
  'use strict';

  angular
    .module('refiereApp.dashboard')
    .controller('SidebarCtrl', SidebarCtrl);

  SidebarCtrl.$inject = ['$scope'];

  function SidebarCtrl($scope) {

    $scope.selectedOption = "";
    $scope.tabs = [{
      title: 'Campaña',
      state: 'dashboard.campaign',
      icon: 'fa-dashboard'
    },{
      title: 'Métricas',
      state: 'dashboard.metrics',
      icon: 'fa-bar-chart-o'
    },{
      title: 'Base de datos',
      state: 'dashboard.database',
      icon: 'fa-tasks'
    },{
      title: 'Noticias',
      state: 'home',
      icon: 'fa-book'
    },{
      title: 'Reportes',
      state: 'home',
      icon: 'fa-cogs'
    }];


  $scope.getOptionSelected = function(selected){
    $scope.seletedOption = selected;
    console.log(selected);
  };
  }
})();