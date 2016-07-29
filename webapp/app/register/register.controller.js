(function() {
  'use strict';

  angular
    .module('refiereApp.register')
    .controller('RegisterCtrl', RegisterCtrl);

  RegisterCtrl.$inject = ['ngProgressFactory', 'RegisterSrv',
                          '$state', '$window'];

  /* @ngInject */
  function RegisterCtrl(ngProgressFactory, RegisterSrv, $state, $window) {
    var vm = this;

    vm.newCompanyData = {};
    vm.saveNewCompany = saveNewCompany;
    vm.plans = {};
    vm.progressbar = ngProgressFactory.createInstance();

    getPlans();

    function getPlans() {
      RegisterSrv.getPlansFromServer()
        .then(function(plansData){
          vm.plans = plansData.data;
          vm.newCompanyData = {
            PlanRequest: {
              id:''
            }
          };
        })
    }

    function saveNewCompany() {
      vm.progressbar.setHeight('0.5em');
      vm.progressbar.setColor('blue');
      vm.progressbar.start();
      RegisterSrv.postCompanyInfo(vm.newCompanyData)
        .then(function(data) {
          if (data.status === 200){
            vm.progressbar.complete();
            $window.alert('Bienvenido ' + vm.newCompanyData.UserRequest.login + '.' );
            $state.go('home');
          }
          else if (data.status === 400){
            vm.progressbar.complete();
            vm.newCompanyData = null;
            $window.alert('Por favor ingrese los datos correctos.');
          }
          else if (data.status === -1){
            vm.progressbar.complete();
            vm.newCompanyData = null;
            $window.alert('¡El usuario ya existe!');
          }
          else{
            vm.progressbar.complete();
            vm.newCompanyData = null;
            $window.alert('Ocurrió un error con la conexión');
          }
        })
        .catch(function(error) {
          vm.progressbar.complete();
          vm.newCompanyData = null;
          $window.alert('¡El usuario ya existe, intentelo con otro usuario!');
        });
    }
  }

})();
