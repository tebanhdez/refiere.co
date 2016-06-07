(function() {
  'use strict';

  angular
    .module('refiereApp.campaign')
    .controller('CampaignCtrl', CampaignCtrl);

  CampaignCtrl.$inject = ['CampaignService'];

  /* @ngInject */
  function CampaignCtrl(CampaignService) {
    var vm = this;

    vm.prizes = {};
    vm.databases = {};
    vm.newCampaign = {};

    getPrizes();
    getDatabases();

    function getPrizes() {
      CampaignService.getPrizeFromServer()
        .then(function(prizesData){
          vm.prizes = prizesData.data;
        })
    }

    function getDatabases() {
      CampaignService.getDatabaseFromServer();
        // .then(function(databasesData){
        //   vm.databases = databasesData.data;
        // })
    }

  }
})();
