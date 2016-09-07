(function() {
  'use strict';

  angular
    .module('refiereApp.metrics')
    .service('MetricService', MetricService);

  MetricService.$inject = ['$http', 'SessionModel', 'UserDataService'];

  /* @ngInject */
  function MetricService($http, SessionModel, UserDataService) {

    this.getReferredFromServer = getReferredFromServer;
    this.getCampaignFromServer = getCampaignFromServer;
    this.getCompanyPrizeFromServer = getCompanyPrizeFromServer;
    this.redeemedCodes = redeemedCodes;
    this.getNotRedeemedCodes = getNotRedeemedCodes;
    this.getCompanyCampaignsFromServer = getCompanyCampaignsFromServer;
    this.getRedeemCodeReportFromServer = getRedeemCodeReportFromServer;
    var currentUser = UserDataService.getUserName();
    var encodedBasic = SessionModel.password;
    var companyId = UserDataService.getCompanyID();

    function getReferredFromServer() {

      var request = {
        method: 'GET',
        url: '/rest/v1/metrics/referredAmount',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': encodedBasic
        }
      };

      return $http(request);
    }

    function getCampaignFromServer() {
      var urlCompany = '/rest/v1/metrics/company/'+companyId+'/campaignsAmount';

      var request = {
        method: 'GET',
        url: urlCompany,
        headers: {
          'Content-Type': 'application/json',
          'Authorization': encodedBasic
        }
      };

      return $http(request);
    }

    function redeemedCodes() {
      var urlCompany = '/rest/v1/metrics/company/'+companyId+'/referresAmount';

      var request = {
        method: 'GET',
        url: urlCompany,
        headers: {
          'Content-Type': 'application/json',
          'Authorization': encodedBasic
        }
      };

      return $http(request);
    }

    function getNotRedeemedCodes() {
      var urlCompany = '/rest/v1/metrics/company/'+companyId+'/notReferredAmount';

      var request = {
        method: 'GET',
        url: urlCompany,
        headers: {
          'Content-Type': 'application/json',
          'Authorization': encodedBasic
        }
      };

      return $http(request);
    }

    function getCompanyPrizeFromServer() {
      var urlCompanyPrize = '/rest/v1/metrics/company/'+companyId+'/campaignsAmountPrize';

      var request = {
        method: 'GET',
        url: urlCompanyPrize,
        headers: {
          'Content-Type': 'application/json',
          'Authorization': encodedBasic
        }
      };

      return $http(request);
    }

    function getCompanyCampaignsFromServer() {
      var urlCompanyCampaign = '/rest/v1/campaign/company/'+companyId+'/campaignList';

      var request = {
        method: 'GET',
        url: urlCompanyCampaign,
        headers: {
          'Content-Type': 'application/json',
          'Authorization': encodedBasic
        }
      };

      return $http(request);
    }

    function getRedeemCodeReportFromServer(campaignId) {
      var urlCompanyCampaign = '/rest/v1/metrics/company/'+campaignId+'/redeemCodeReport';

      var request = {
        method: 'GET',
        url: urlCompanyCampaign,
        headers: {
          'Content-Type': 'application/json',
          'Authorization': encodedBasic
        }
      };

      return $http(request);
    }

  }

})();
