(function() {
  'use strict';

  angular
    .module('refiereApp.campaign')
    .controller('CampaignCtrl', CampaignCtrl);

  CampaignCtrl.$inject = ['DatabaseService', 'CampaignService', 'UserDataService', '$window',
                          'SessionModel', '$scope', 'Upload', '$timeout'];

  /* @ngInject */
  function CampaignCtrl(DatabaseService, CampaignService, UserDataService, $window,
                        SessionModel, $scope, Upload, $timeout) {
    var vm = this;

    vm.prizes = {};
    vm.databases = {};
    vm.newCampaign = {};
    vm.newDatabase = {};

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

    $scope.uploadFiles = function(files, errFiles) {
      $scope.files = files;
      $scope.errFiles = errFiles;
      angular.forEach(files, function(file) {
          var actualURL = DatabaseService.newURL();
          var encodedBasic = SessionModel.password;

          file.upload = Upload.upload({
            url: actualURL,
            method: 'POST',
            data: {
              filename: file.name, // this is needed for Flash polyfill IE8-9
              file: file
            },
            headers: {
              'Content-Type': file.type != '' ? file.type : 'application/octet-stream',
              'Authorization': encodedBasic
            }
        });

        file.upload.then(function (response) {
            $timeout(function () {
                file.result = response.data;
            });
        }, function (response) {
            if (response.status > 0)
                $scope.errorMsg = response.status + ': ' + response.data;
        }, function (evt) {
            file.progress = Math.min(100, parseInt(100.0 *
                                     evt.loaded / evt.total));
        });

      });
  }

    function createNewCampaign() {
      vm.newCampaign.companyId = UserDataService.getCompanyID();
      vm.newCampaign.companyDataBase = "1"; 
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
