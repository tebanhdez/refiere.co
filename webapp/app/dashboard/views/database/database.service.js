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

    function postNewDatabase(databaseRef) {
      // var fd = new FormData();
      // fd.append('file', file);
      // var url = '/rest/database/' + companyId + '/import';
      // return $http.post(url, fd, {
      //        headers: {'Authorization': encodedBasic}
      //    });
     var databaseReference = '/rest/database/' + companyId + '/import';
     var request = {
       method: 'POST',
       url: databaseReference,
       headers: {
         'Content-Type': 'application/json',
         'Authorization': encodedBasic
       },
       data: databaseRef
     };

     return $http(request);
     }

     function newURL() {
       var url = '/rest/database/' + companyId + '/import';
       return url;
      }

  }

})();
