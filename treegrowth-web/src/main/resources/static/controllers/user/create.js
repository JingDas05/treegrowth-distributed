'use strict';

angular.module('treeGrowth').controller('UserCreateController', function UserCreateController(User) {
    var vm = this;
    vm.submit = function (form) {
        User.save(form,
            function (user) {
                console.log(user);
            },
            function (resp) {
                console.log(resp.data);
            }
        );
        console.log(form);
    };
});