(function() {
  'use strict';

  angular
    .module('refiereApp.login')
    .controller('LoginCtrl', LoginCtrl);

  function LoginCtrl($state) {
    var vm = this;
    vm.login = login;

    function login() {
      $state.go('dashboard');
    }
  }
})();