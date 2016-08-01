(function() {
  'use strict';

  angular
    .module('refiereApp.pendingOrders')
    .service('OrdersService', OrdersService);

  OrdersService.$inject = ['$http', 'SessionModel', 'UserDataService'];

  /* @ngInject */
  function OrdersService($http, SessionModel, UserDataService) {

    this.getAllOrders = getAllOrders;
    var encodedBasic = SessionModel.password;

    function getAllOrders() {

      var request = {
        method: 'GET',
        url: '/rest/v1/order/all',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': encodedBasic
        }
      };

      return $http(request);
    }

  }

})();
