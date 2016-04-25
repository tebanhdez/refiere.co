(function() {
  'use strict';

  angular
    .module('refiereApp.dashboard')
    .controller('SidebarCtrl', SidebarCtrl);

  function SidebarCtrl() {
    this.tabs = [{
      title: 'Campaña',
      state: 'home',
      icon: 'fa-dashboard'
    },{
      title: 'Métricas',
      state: 'campaign',
      icon: 'fa-bar-chart-o'
    },{
      title: 'Base de datos',
      state: 'home',
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
  }
})();