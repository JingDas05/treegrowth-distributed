'use strict';

angular.module('treeGrowth').config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

    $stateProvider.state('default', {
        url: '',
        templateUrl: 'controllers/home/index.html',
        controller: 'HomeIndexController as vm'
    });

    $stateProvider.state('home', {
        url: '/',
        templateUrl: 'controllers/home/index.html',
        controller: 'HomeIndexController as vm'
    });

    $stateProvider.state('notFound', {
        url: '/notFound',
        templateUrl: 'controllers/home/notFound.html',
        controller: 'HomeNotFoundController as vm'
    });

    $stateProvider.state('upload', {
        url: '/upload',
        templateUrl: 'controllers/upload/upload.html',
        controller: 'UpLoadController as vm'
    });

    $urlRouterProvider.otherwise('/notFound');

    $stateProvider.state('user', {
        url: '/user',
        template: '<div ui-view></div>',
        abstract: true
    });
    $stateProvider.state('user.create', {
        url: '/create',
        templateUrl: 'controllers/user/create.html',
        controller: 'UserCreateController as vm'
    });
    $stateProvider.state('user.list', {
        url: '/list',
        templateUrl: 'controllers/user/list.html',
        controller: 'UserListController as vm'
    });
    $stateProvider.state('user.detail', {
        url: '/detail',
        templateUrl: 'controllers/user/detail.html',
        controller: 'UserDetailController as vm'
    });
}]);
