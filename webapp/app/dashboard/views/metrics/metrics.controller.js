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
    vm.notNullReferences = {};
    vm.nullReferences = {};
    vm.companyPrize = {};

    getReferred();
    getCampaign();
    getCompanyPrize();
    getNullReferences();
    getNotNullReferences();

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

    function getNullReferences() {
      MetricService.getReferencesCodesNull()
      .then(function(referencesNull){
        vm.nullReferences = referencesNull.data;
        console.log(vm.nullReferences);
      })
    }

    function getNotNullReferences() {
      MetricService.getReferencesCodesNotNull()
      .then(function(referencesNotNull){
        vm.notNullReferences = referencesNotNull.data;
        console.log(vm.notNullReferences);
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
