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
    var currentUser = UserDataService.getUserName();
    var encodedBasic = SessionModel.password;

    function getPrizeFromServer() {

      var request = {
        method: 'GET',
        url: '/rest/v1/prize/all',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': encodedBasic
        }
      };

      return $http(request);
    }

    function getDatabaseFromServer() {

      var request = {
        method: 'POST',
        url: '/rest/v1/database/all',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': encodedBasic
        },
        data: currentUser
      };

      return $http(request);
    }

    function setNewCampaign(campaignData) {
      var postRequest = {
        method: 'POST',
        url: '/rest/v1/campaign',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': encodedBasic
        },
        data: campaignData
      };

      return $http(postRequest);
    }

  }

})();
