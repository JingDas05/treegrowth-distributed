'use strict';

angular.module('treeGrowth').controller('AppLayoutController',function AppLayoutController() {
    var vm = this;
});

angular.module('treeGrowth').directive('appLayout',function () {
    return {
        restrict: 'EA',
        scope: {},
        templateUrl: 'components/layout/_layout.html',
        controller: 'AppLayoutController as vm'
    }
});
