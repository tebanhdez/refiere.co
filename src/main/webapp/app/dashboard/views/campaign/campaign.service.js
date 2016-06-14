(function() {
  'use strict';

  angular
    .module('refiereApp.campaign')
    .service('CampaignService', CampaignService);

  CampaignService.$inject = ['$http', 'SessionModel', 'UserDataService'];

  /* @ngInject */
  function CampaignService($http, SessionModel, UserDataService) {

    this.getPrizeFromServer = getPrizeFromServer;
    this.getDatabaseFromServer = getDatabaseFromServer;
    this.setNewCampaign = setNewCampaign;

    function getPrizeFromServer(data) {
      return $http.get('http://localhost:5000/rest/v1/prize/all', data);
    }

    function getDatabaseFromServer() {

      var currentUser = UserDataService.getUserName();
      var encodedBasic = SessionModel.password;

      var request = {
        method: 'POST',
        url: 'http://localhost:5000/rest/v1/database/all',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': encodedBasic
        },
        data: currentUser
      };

      return $http(request);
    }

    function setNewCampaign(campaignData) {
      return $http.post('http://localhost:5000/rest/v1/campaign', campaignData);
    }

  }

})();
