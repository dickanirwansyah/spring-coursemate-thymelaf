var myApp = angular.module('myApp', ['ngResource']);

myApp.config(function($resourceProvider){
    $resourceProvider.defaults.stripTrailingSlashes = false;
});

myApp.controller('loginCtrl', function($scope){

     console.log('AngularJS Kolaborasi Thymeleaf');

     $scope.username = "";

});