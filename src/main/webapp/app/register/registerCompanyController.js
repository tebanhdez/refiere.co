'use strict';

/**
 * @ngdoc function
 * @name refiereApp.controller:RegisterCompanyCtrl
 * @description
 * # RegisterCompanyCtrl
 * Controller of the refiereApp
 */
angular.module('refiereApp')
  .controller('RegisterCompanyCtrl', function ($scope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
    var vm = this;
    vm.companyInfo = {};
    vm.registerCompany = registerCompany;

    function registerCompany(){
    	AddCompanyService.createNewCompany(vm.companyInfo)
    	  .then(handleCompanySaved)
    	  .catch(handleSavingCompanyError);
    }
    function handleCompanySaved(){
      $scope.alerts.push({msg:"La compañía fue creada exitosamente"});
    };
    function handleSavingCompanyError(){
      $scope.alerts.push({msg:"Error al registrar la compañía"});
    }
  });
