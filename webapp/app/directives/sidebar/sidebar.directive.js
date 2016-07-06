(function() {

  angular
    .module('refiereApp.dashboard')
    .directive('sidebar', sidebarDirective);

  function sidebarDirective() {
    var directive = {
      restrict: 'E',
      templateUrl: 'app/directives/sidebar/view.html',
      controller: 'SidebarCtrl as vm'
    };

    return directive;
  }

})();
