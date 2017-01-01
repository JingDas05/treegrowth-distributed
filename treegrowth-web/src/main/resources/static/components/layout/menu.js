'use strict';

'use strict';

angular.module('treeGrowth').controller('LayoutMenuController', function LayoutMenuController() {
    var vm = this;
});
angular.module('treeGrowth').directive('layoutMenu', function LayoutMenu() {
    return {
        restrict: 'EA',
        scope: {},
        templateUrl: 'components/layout/menu.html',
        controller: 'LayoutMenuController as vm'
    };
});
