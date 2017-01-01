'use strict';

angular.module('treeGrowth').controller('UserListController', function UserListController(User) {

    var vm = this;
    User.query(function (Users) {
        vm.items = Users;
    });

});
