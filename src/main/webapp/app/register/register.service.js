(function() {
  'use strict';

  angular
    .module('refiereApp.register')
    .service('RegisterSrv', RegisterSrv);

  RegisterSrv.$inject = ['$http'];

  /* @ngInject */
  function RegisterSrv($http) {

    this.postCompanyInfo = postCompanyInfo;

    function postCompanyInfo(data) {
      return $http.post('http://localhost:5000/rest/v1/company/register', data);
    };
  }
})();
