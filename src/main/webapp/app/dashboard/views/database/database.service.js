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
    // var url_encoded = "http://localhost:5000/rest/database/" + companyId + "/import";

    // function setNewCampaign(campaignData) {
    //   var postRequest = {
    //     method: 'POST',
    //     url: url_encoded,
    //     headers: {
    //       'Content-Type': 'application/json',
    //       'Authorization': encodedBasic
    //     },
    //     data: campaignData
    //   };
    //
    //   return $http(postRequest);
    // }

    function postNewDatabase(file) {
      var fd = new FormData();
      fd.append('file', file);
      var url = 'http://localhost:5000/rest/database/' + companyId + '/import';
      console.log(file);
      console.log(url);
      return $http.post(url, fd, {
             headers: {'Authorization': encodedBasic}
         });
     }

     function newURL() {
       var url = 'http://localhost:5000/rest/database/' + companyId + '/import';
       return url;
      }

    // function postNewArtist(data,file){
    //    var jsonData = JSON.stringify(data);
    //    console.log(jsonData);
    //    var fd = new FormData();
    //      fd.append('file', file);
    //      fd.append('data',jsonData);
    //   var url = 'http://localhost:8000/rest/v1/register/artist';
    //   return $http.post(url, fd, {
    //          transformRequest: angular.identity,
    //          headers: {'Content-Type': undefined}
    //      });
    //  }

  }

})();
