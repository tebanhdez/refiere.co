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
    vm.plan = {};

    getPlans();

    function getPlans() {
      RegisterSrv.getPlansFromServer()
        .then(function(plansData){
          vm.plan = plansData.data[1];
          vm.newCompanyData = {
            PlanRequest: {
              id:  vm.plan.planId
            }
          };
        })
    }

    function saveNewCompany() {
      RegisterSrv.postCompanyInfo(vm.newCompanyData)
        .then(function(data) {
          if (data.status === 200){
            $window.alert('Bienvenido ' + vm.newCompanyData.UserRequest.login + '.' );
            $state.go('home');
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
          $window.alert('¡El usuario ya existe, intentelo con otro usuario!');
        });
    }
  }

})();
