(function() {
    'use strict';

    angular
        .module('refiereApp.registerNewReferred')
        .controller('RegisterNewReferredCtrl', RegisterNewReferredCtrl);

    RegisterNewReferredCtrl.$inject = ['RegisterNewReferredSrv', '$state', '$window'];

    /* @ngInject */
    function RegisterNewReferredCtrl(RegisterNewReferredSrv, $state, $window) {
        var vm = this;

        vm.newReferredData = {};
        vm.saveNewReferred = saveNewReferred;

        function saveNewReferred() {
            console.log(vm.newReferredData);

            RegisterNewReferredSrv.postNewUserInfo(vm.newReferredData)
                .then(function(data) {
                    console.log(data.status);
                    if (data.status === -1){
                        $window.alert('¡El usuario ya existe!');
                    }
                    else{
                        $window.alert('Usuario registrado con exito');
                    }
                })
                .catch(function(error) {
                    console.log(error);
                    $window.alert('¡Ocurrio un error!');
                });
        }
    }

})();
