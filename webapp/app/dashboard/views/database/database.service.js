(function() {
  'use strict';

  angular
    .module('refiereApp.database')
    .service('DatabaseService', DatabaseService);

  DatabaseService.$inject = ['$http', 'SessionModel', 'UserDataService'];

  /* @ngInject */
  function DatabaseService($http, SessionModel, UserDataService) {

    this.postNewDatabase = postNewDatabase;
    this.newURL = newURL;
    var currentUser = UserDataService.getUserName();
    var encodedBasic = SessionModel.password;
    var companyId = UserDataService.getCompanyID();

    function postNewDatabase(file) {
      var fd = new FormData();
      fd.append('file', file);
      var url = 'https://pacific-sea-18743.herokuapp.com/rest/database/' + companyId + '/import';
      return $http.post(url, fd, {
             headers: {'Authorization': encodedBasic}
         });
     }

     function newURL() {
       var url = 'https://pacific-sea-18743.herokuapp.com/rest/database/' + companyId + '/import';
       return url;
      }

  }

})();
