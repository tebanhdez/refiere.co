(function() {
  'use strict';

  angular
    .module('refiereApp.login')
    .controller('LoginCtrl', LoginCtrl);

  LoginCtrl.$inject = ['$state', 'LoginSrv', '$window', '$base64'];

  /* @ngInject */
  function LoginCtrl($state, LoginSrv, $window, $base64) {
    var vm = this;

    vm.loginData = {};
    vm.login = login;

    function login() {
      console.log(vm.loginData);
      var userDataString = vm.loginData.username + ':' + vm.loginData.password;
      console.log(userDataString);
      var encodedData = $base64.encode(userDataString);
      console.log(encodedData);

      LoginSrv.verifyUser(vm.loginData)
        .then(function(data) {
          console.log(data.status);

          if (data.status === 200){
            // $window.alert("Bienvenido " + vm.newCompanyData.UserRequest.login + "." );
            $state.go('app.dashboard');
          }
          else if (data.status === 400){
            $window.alert('Por favor ingrese los datos correctos.');
          }
          else{
            $window.alert('Ocurrió un error con la conexión');
          }
        })
        .catch(function(error) {
          console.log(error);
        });

      // $state.go('app.dashboard');
    }

  }

})();
