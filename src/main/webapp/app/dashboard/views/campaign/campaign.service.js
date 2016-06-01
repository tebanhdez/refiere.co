(function() {
  'use strict';

  angular
    .module('refiereApp.campaign')
    .service('CampaignService', CampaignService);

  CampaignService.$inject = ['$http', '$base64', 'UserDataService'];

  /* @ngInject */
  function CampaignService($http, $base64, UserDataService) {

    this.getPrizeFromServer = getPrizeFromServer;
    this.getDatabaseFromServer = getDatabaseFromServer;
    this.setNewCampaign = setNewCampaign;

    function getPrizeFromServer(data) {
      return $http.get('http://localhost:5000/rest/v1/prize/all', data);
    }

    function getDatabaseFromServer(data) {
      return $http.get('http://localhost:5000/rest/v1/database/all', data);
    }

    function setNewCampaign(campaignData) {
      return $http.post('http://localhost:5000/rest/v1/campaign', campaignData);
    }

  }

})();
