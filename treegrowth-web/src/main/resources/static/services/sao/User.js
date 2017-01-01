'use strict';

angular.module('treeGrowth').factory('User', ['$resource', function UserFactory($resource) {

    return $resource('/api/users/:id', {id: '@userId'});
}]);
