(function() {
  'use strict';

  angular
    .module('refiereApp.dashboard')
    .controller('SidebarCtrl', SidebarCtrl);

  SidebarCtrl.$inject = ['UserDataService'];

  function SidebarCtrl(UserDataService) {
    var vm = this;
    vm.userData = UserDataService.getAllUserData();

    vm.selectedOption = "";
    vm.tabs = [{
      title: 'Campaña',
      state: 'dashboard.campaign',
      icon: 'fa-dashboard'
    },{
      title: 'Métricas',
      state: 'dashboard.metrics',
      icon: 'fa-bar-chart-o'
    },{
      title: 'Ordenes Pendientes',
      state: 'dashboard.pendingOrders',
      icon: 'fa-calendar-o'
    },{
      title: 'Noticias',
      state: 'dashboard.news',
      icon: 'fa-book'
    },{
      title: 'Reportes',
      state: 'dashboard.reports',
      icon: 'fa-cogs'
    }];

    vm.getOptionSelected = function(selected){
      vm.seletedOption = selected;
    };

  }
})();
