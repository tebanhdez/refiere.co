(function() {
  'use strict';

  angular
    .module('refiereApp.login')
    .controller('LoginCtrl', LoginCtrl);

  LoginCtrl.$inject = ['ngProgressFactory', '$state',
                       'LoginSrv', '$window', 'UserDataService'];

  /* @ngInject */
  function LoginCtrl(ngProgressFactory, $state,
                     LoginSrv, $window, UserDataService) {
    var vm = this;

    vm.loginData = {};
    vm.login = login;
    vm.register = register;
    vm.progressbar = ngProgressFactory.createInstance();

    function login() {
      vm.progressbar.setHeight('0.5em');
      vm.progressbar.setColor('blue');
      vm.progressbar.start();
      LoginSrv.verifyUser(vm.loginData)
        .then(function(data) {

          var userInfo = data.data;
          userInfo.name = vm.loginData.login;
          UserDataService.setUserInfoData(userInfo);

          if (data.status === 200){
            vm.progressbar.complete();
            $state.go('dashboard');
          }

        })
        .catch(function(error) {
          vm.progressbar.complete();
          vm.loginData = null;
          $window.alert('Por favor ingrese los datos correctos.');
        });
    }
    function register(){
      vm.progressbar.complete();
      vm.loginData = null;
      $state.go('register');
    }
  }

})();
