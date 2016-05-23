(function() {
  'use strict';

  angular
    .module('refiereApp.dashboard')
    .controller('SidebarCtrl', SidebarCtrl);

  SidebarCtrl.$inject = ['$scope', 'UserDataService'];

  function SidebarCtrl($scope, UserDataService) {

    var vm = this;

    vm.userData = UserDataService.getAllUserData();
    document.getElementById("userName").innerHTML = "<strong style='color: white;'>" + vm.userData.name + "</strong>"
    console.log(vm.userData.name);

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
      state: 'dashboard.news',
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
