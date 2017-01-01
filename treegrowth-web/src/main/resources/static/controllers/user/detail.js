'use strict';

angular.module('treeGrowth').controller('UserDetailController', ['User', function UserDetailController(User) {

    var vm = this;
    vm.get = function (id) {
        var user = User.get({userId: id}, function (User) {
            vm.item = User;
        });
        console.log(User)
    };
}]);

