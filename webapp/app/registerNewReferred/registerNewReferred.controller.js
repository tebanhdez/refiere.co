(function() {
    'use strict';

    angular
        .module('refiereApp.registerNewReferred')
        .controller('RegisterNewReferredCtrl', RegisterNewReferredCtrl);

    RegisterNewReferredCtrl.$inject = ['RegisterNewReferredSrv', '$state',
                                       '$window'];

    /* @ngInject */
    function RegisterNewReferredCtrl(RegisterNewReferredSrv, $state, $window) {
        var vm = this;

        vm.newReferredData = {};
        vm.saveNewReferred = saveNewReferred;

        function saveNewReferred() {
          console.log(vm.newReferredData);
          RegisterNewReferredSrv.postNewUserInfo(vm.newReferredData)
            .then(function(data) {
              console.log(data);
              console.log(data.status);
              if (data.data.status === "OK"){
                vm.newReferredData = {};
                $window.alert('Código cangeado con exito');
              }
              else{
                $window.alert('¡Ocurrio un error! Por favor revise los datos ingresados');
              }
            })
            .catch(function(error) {
              $window.alert('¡Ocurrio un error! Por favor asegurese de estar conectado a internet');
            });
        }
    }
})();
