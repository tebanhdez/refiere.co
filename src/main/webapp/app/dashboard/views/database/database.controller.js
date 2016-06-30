(function() {
  'use strict';

  angular
    .module('refiereApp.database')
    .controller('DatabaseCtrl', DatabaseCtrl);

  DatabaseCtrl.$inject = ['DatabaseService', 'UserDataService', '$window',
                          'SessionModel', '$scope', 'Upload', '$timeout'];

  /* @ngInject */
  function DatabaseCtrl(DatabaseService, UserDataService, $window,
                        SessionModel, $scope, Upload, $timeout) {
    var vm = this;

    vm.newDatabase = {};
    vm.createNewDatabase = createNewDatabase;

    function createNewDatabase() {
      console.log(vm.newDatabase);
      DatabaseService.postNewDatabase(vm.newDatabase)
        .then(function(data) {
          if (data.status === 200){
            $window.alert('Has importado una nueva base de datos con exito' );
          }
          else{
            $window.alert('Ocurrió un error al importar el archivo');
          }
        })
        .catch(function(error) {
          console.log(error);
          $window.alert('Ocurrió un error con la conexión');
        });
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
  }
})();
