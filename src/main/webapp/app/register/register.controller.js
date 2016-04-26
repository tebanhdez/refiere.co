(function() {
  'use strict';

  angular
    .module('refiereApp.register')
    .controller('RegisterCtrl', RegisterCtrl);

  RegisterCtrl.$inject = ['RegisterSrv', '$state', '$window'];

  /* @ngInject */
  function RegisterCtrl(RegisterSrv, $state, $window) {
    var vm = this;

    vm.newCompanyData = {};
    vm.saveNewCompany = saveNewCompany;

    function saveNewCompany() {
      console.log(vm.newCompanyData);

      RegisterSrv.postCompanyInfo(vm.newCompanyData)
        .then(function(data) {
          console.log(data.status);
          if (data.status === 200){
            $window.alert("Bienvenido " + vm.newCompanyData.UserRequest.login + "." );
            $state.go('app.dashboard');
          }
          else if (data.status === 400){
            $window.alert("Por favor ingrese los datos correctos." );
          }
          else{
            $window.alert("Ocurrió un error con la conexión" );
          }
        })
        .catch(function(error) {
          console.log(error);
        });
    }
  }

})();
