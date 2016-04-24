'use strict';

/**
 * @ngdoc function
 * @name refiereApp.services:AddCompanyService
 * @description
 * # AddCompanyService
 * Service of the refiereApp
 */
angular.module('refiereApp')
  .services('AddCompanyService', function ($scope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];

    var this = vm;
    vm.createNewCompany = createNewCompany;

    function createNewCompany(companyInfo) {
      $scope.alerts.push(companyInfo);
      
    }

   });
