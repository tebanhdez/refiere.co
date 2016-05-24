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
          else if (data.status === 400){
            $window.alert('Por favor ingrese los datos correctos.');
          }
          else if (data.status === 500){
            $window.alert('El usuario y/o la contraseña no son correctos.');
          }
          else{
            $window.alert('Ocurrió un error con la conexión');
          }
        })
        .catch(function(error) {
          console.log(error);
        });
    }
  }

})();
