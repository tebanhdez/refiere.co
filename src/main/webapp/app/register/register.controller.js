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
    vm.plans = {};

    getPlans();

    function getPlans() {
      RegisterSrv.getPlansFromServer()
        .then(function(plansData){
          vm.plans = plansData.data;
          console.log(vm.plans);
        })
    }

    function saveNewCompany() {
      console.log(vm.newCompanyData);

      RegisterSrv.postCompanyInfo(vm.newCompanyData)
        .then(function(data) {
          console.log(data.status);
          if (data.status === 200){
            $window.alert('Bienvenido ' + vm.newCompanyData.UserRequest.login + '.' );
            $state.go('app.dashboard');
          }
          else if (data.status === 400){
            $window.alert('Por favor ingrese los datos correctos.');
          }
          else if (data.status === -1){
            $window.alert('¡El usuario ya existe!');
          }
          else{
            $window.alert('Ocurrió un error con la conexión');
          }
        })
        .catch(function(error) {
          console.log(error);
          $window.alert('¡El usuario ya existe!');
        });
    }
  }

})();
