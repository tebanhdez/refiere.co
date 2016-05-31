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

    getPrizes();

    function getPrizes() {
      CampaignService.getPrizeFromServer()
        .then(function(prizesData){
          vm.prizes = prizesData.data;
        })
    }

  }
})();
