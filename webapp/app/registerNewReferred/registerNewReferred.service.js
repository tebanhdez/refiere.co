(function() {
    'use strict';

    angular
        .module('refiereApp.registerNewReferred')
        .service('RegisterNewReferredSrv', RegisterNewReferredSrv);

    RegisterNewReferredSrv.$inject = ['$http'];

    /* @ngInject */
    function RegisterNewReferredSrv($http) {

        this.postNewUserInfo = postNewUserInfo;

        function postNewUserInfo(data) {
            return $http.post('http://refiere.net/rest/v1/redeemCode/registerUser', data);
        }
    }
})();
