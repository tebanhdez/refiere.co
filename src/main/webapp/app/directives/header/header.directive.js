(function() {

  angular
    .module('refiereApp.dashboard')
    .directive('header', headerDirective);

  function headerDirective() {
    var directive = {
      restrict: 'E',
      templateUrl: 'app/directives/header/view.html',
    };

    return directive;
  }

})();
