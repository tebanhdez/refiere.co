(function() {
  'use strict';

  angular
    .module('refiereApp.login')
    .controller('LoginCtrl', LoginCtrl);

  LoginCtrl.$inject = ['$state', 'LoginSrv', '$window', 'UserDataService'];

  /* @ngInject */
  function LoginCtrl($state, LoginSrv, $window, UserDataService) {
    var vm = this;

    vm.loginData = {};
    vm.login = login;

    function login() {

      LoginSrv.verifyUser(vm.loginData)
        .then(function(data) {

          var userInfo = data.data;
          userInfo.name = vm.loginData.login;
          UserDataService.setUserInfoData(userInfo);

          if (data.status === 200){
            $state.go('dashboard');
          }

        })
        .catch(function(error) {
          $window.alert('Por favor ingrese los datos correctos.');
        });
    }
  }

})();
