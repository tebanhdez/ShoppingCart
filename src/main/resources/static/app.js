var marketApp = angular.module('market', []);

marketApp.controller('MainController', function($http, $window) {
    var vm = this;

    vm.products = [{'sku': 'A', 'name':'Apples', 'price': '25ct'}, {'sku': 'O', 'name':'Oranges', 'price':'30ct'}, {'sku': 'B', 'name':'Bananas', 'price':'15ct'}, {'sku': 'P', 'name':'Papayas', 'price':'50ct'}];
    vm.selectedItems = [];
    vm.totalAmount = 0;

    vm.selectItem = function(item) {
        vm.selectedItems.push({'sku': item.sku, 'quantity': 1, 'name': item.name, 'price': item.price});
        vm.checkOut();
    };

    vm.delete = function(item) {
        var index = vm.selectedItems.indexOf(item);
        if(index > -1){
            vm.selectedItems.splice(index, 1); 
        }
        vm.checkOut();
    };

    vm.checkOut = function() {
        var order = {};
        var orderedItems = [];
        angular.forEach(vm.selectedItems, function(item){
            if(!order[item.sku]){
                order[item.sku] = item.quantity;
            } else{
                order[item.sku] = item.quantity + order[item.sku];
            }
        });
        angular.forEach(order, function(value, key){
            orderedItems.push({'sku': key, 'quantity': value});
        });
        if(vm.selectedItems.length > 0)
            vm.calculateTotal(orderedItems);
        else
            vm.totalAmount = 0;
    };

    vm.calculateTotal = function(orderedItemsJson) {
        $http.post('/cart/total', orderedItemsJson)
        .then(function(response) {
            vm.totalAmount = response.data;
        });
    };
});