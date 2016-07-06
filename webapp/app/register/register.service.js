(function() {
  'use strict';

  angular
    .module('refiereApp.register')
    .service('RegisterSrv', RegisterSrv);

  RegisterSrv.$inject = ['$http'];

  /* @ngInject */
  function RegisterSrv($http) {

    this.postCompanyInfo = postCompanyInfo;
    this.getPlansFromServer = getPlansFromServer;

    function postCompanyInfo(data) {
      return $http.post('https://pacific-sea-18743.herokuapp.com/rest/v1/company/register', data);
    }

    function getPlansFromServer(data) {
      return $http.get('https://pacific-sea-18743.herokuapp.com/rest/v1/plan/all', data);
    }
  }
})();
