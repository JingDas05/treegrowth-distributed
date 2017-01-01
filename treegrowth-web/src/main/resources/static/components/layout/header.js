'use strict';

angular.module('treeGrowth').controller('LayoutHeaderController', function () {
    var vm = this;
});

angular.module('treeGrowth').directive('layoutHeader', function LayoutHeader() {
    return {
        restrict: 'EA',
        scope: {},
        template: 'components/layout/header.html',
        controller: 'LayoutHeaderController as vm'
    }
});