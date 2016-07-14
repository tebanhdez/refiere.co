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
      return $http.post('https://refiere.herokuapp.com/rest/v1/company/register', data);
    }

    function getPlansFromServer(data) {
      //https://refiere.herokuapp.com
      return $http.get('https://refiere.herokuapp.com/rest/v1/plan/all', data);
    }
    
  }
})();
