(function() {
  'use strict';

  angular
    .module('refiereApp.campaign')
    .controller('CampaignCtrl', CampaignCtrl);

  CampaignCtrl.$inject = ['CampaignService', 'UserDataService', '$window'];

  /* @ngInject */
  function CampaignCtrl(CampaignService, UserDataService, $window) {
    var vm = this;

    vm.prizes = {};
    vm.databases = {};
    vm.newCampaign = {};

    vm.createNewCampaign = createNewCampaign;

    getPrizes();
    getDatabases();

    function getPrizes() {
      CampaignService.getPrizeFromServer()
        .then(function(prizesData){
          vm.prizes = prizesData.data;
        })
    }

    function getDatabases() {
      CampaignService.getDatabaseFromServer()
        .then(function(databasesData){
          vm.databases = databasesData.data;
        })
    }

    function createNewCampaign() {
      vm.newCampaign.companyId = UserDataService.getCompanyID();
      CampaignService.setNewCampaign(vm.newCampaign)
        .then(function(data) {
          if (data.status === 200){
            $window.alert('Has creado una nueva campaña con exito.' );
          }
          else{
            $window.alert('Ocurrió un error con la conexión');
          }
        })
        .catch(function(error) {
          console.log(error);
          $window.alert('Ocurrió un error con la conexión');
        });
    }

  }
})();
