(function() {
  'use strict';

  angular
    .module('refiereApp.metrics')
    .controller('MetricsCtrl', MetricsCtrl);

  MetricsCtrl.$inject = ['MetricService', 'UserDataService'];

  /* @ngInject */
  function MetricsCtrl(MetricService, UserDataService) {
    var vm = this;

    vm.referred = {};
    vm.campaign = {};
    vm.notRedeemedCodesAmount = {};
    vm.redeemedCodesAmount = {};
    vm.companyPrize = {};

    getReferred();
    getCampaign();
    getCompanyPrize();
    getRedeemedCodesAmount();
    getNotRedeemedCodesAmount();

    function getReferred() {
      MetricService.getReferredFromServer()
      .then(function(referredData){
        vm.referred = referredData.data;
      })
    }

    function getCampaign() {
      MetricService.getCampaignFromServer()
      .then(function(campaignData){
        vm.campaign = campaignData.data;
      })
    }

    function getRedeemedCodesAmount() {
      MetricService.redeemedCodes()
      .then(function(redeemedCodes){
        vm.redeemedCodesAmount = redeemedCodes.data;
      })
    }

    function getNotRedeemedCodesAmount() {
      MetricService.getNotRedeemedCodes()
      .then(function(notRedeemedCodes){
        vm.notRedeemedCodesAmount = notRedeemedCodes.data;
      })
    }

    function getCompanyPrize() {
      MetricService.getCompanyPrizeFromServer()
      .then(function(companyPrizeData){
        vm.companyPrize = companyPrizeData.data;
      })
    }

  }
})();
