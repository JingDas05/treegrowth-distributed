'use strict';

angular.module('treeGrowth').controller('LayoutFooterController', function () {

    var vm = this;
});

angular.module('treeGrowth').directive('layoutFooter', function LayoutHeader() {
    return {
        restrict: 'EA',
        scope: {},
        template: 'components/layout/footer.html',
        controller: 'LayoutFooterController as vm'
    }
});