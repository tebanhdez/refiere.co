(function() {
  'use strict';

  angular
    .module('refiereApp.pendingOrders')
    .controller('OrdersCtrl', OrdersCtrl);

  OrdersCtrl.$inject = ['OrdersService'];

  /* @ngInject */
  function OrdersCtrl(OrdersService) {

    var vm = this;

    vm.orders = {};

    getPrizes();

    function getPrizes() {
      OrdersService.getAllOrders()
        .then(function(ordersData){
          vm.orders = ordersData.data;
          console.log(vm.orders);
        })
    }

  }
})();
