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
          RegisterNewReferredSrv.postNewUserInfo(vm.newReferredData)
            .then(function(data) {
              if (data.status === -1){
                $window.alert('¡El usuario ya existe!');
              }
              else{
                $window.alert('Usuario registrado con exito');
              }
            })
            .catch(function(error) {
              $window.alert('¡Ocurrio un error!');
            });
        }

    }
})();
