angular.module('jpendel', [])
    .controller('eventController', function ($http, $scope) {
        $http.get('/event').then(function (response) {
            $scope.events = response.data;
        })
    });