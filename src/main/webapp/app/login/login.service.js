(function() {
  'use strict';

  angular
    .module('refiereApp.login')
    .service('LoginSrv', LoginSrv);

  LoginSrv.$inject = ['$http'];

  /* @ngInject */
  function LoginSrv($http) {

    this.verifyUser = verifyUser;

    function verifyUser(userData) {
      return $http.post('http://localhost:5000/rest/v1/auth/login', userData);
    }
  }
})();
