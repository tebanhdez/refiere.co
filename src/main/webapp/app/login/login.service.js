(function() {
  'use strict';

  angular
    .module('refiereApp.login')
    .service('LoginSrv', LoginSrv);

  LoginSrv.$inject = ['$http', '$base64'];

  /* @ngInject */
  function LoginSrv($http, $base64) {

    this.verifyUser = verifyUser;

    function verifyUser(userData) {

      var jsonData = JSON.stringify(userData);
      var userDataString = userData.login + ':' + userData.password;
      var encodedData = $base64.encode(userDataString);
      var encodedBasic = 'Basic ' + encodedData + '';

      var request = {
        method: 'POST',
        url: 'http://localhost:5000/rest/v1/auth/login',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': encodedBasic
        },
        data: jsonData
      };
      return $http(request);
    }


  }
})();
