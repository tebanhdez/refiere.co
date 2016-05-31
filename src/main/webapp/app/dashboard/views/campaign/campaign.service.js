(function() {
  'use strict';

  angular
    .module('refiereApp.campaign')
    .service('CampaignService', CampaignService);

  CampaignService.$inject = ['$http'];

  /* @ngInject */
  function CampaignService($http) {

    this.getPrizeFromServer = getPrizeFromServer;

    function getPrizeFromServer(data) {
      return $http.get('http://localhost:5000/rest/v1/prize/all', data);
    }

  }
})();
