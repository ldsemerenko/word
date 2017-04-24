function MainController($scope, $http, $state) {
    $scope.username_reg = '';
    $scope.password_reg = '';
    $scope.username = '';
    $scope.password = '';

    $scope.reg = function() {
        $http({
            url: '/reg',
            method: 'POST',
            params: {
                username: $scope.username_reg,
                password: $scope.password_reg
            }
        }).success(function (data) {
            console.log(data);
            if (data.ok) {
                $state.go('home');
            }
        }).error(function (data) {
            console.log(data);
        });
    };

    $scope.login = function() {
        $http({
            url: '/login',
            method: 'POST',
            params: {
                username: $scope.username,
                password: $scope.password
            }
        }).success(function (data) {
            console.log(data);
            if (data.ok) {
                $state.go('home');
            }
        }).error(function (data) {
            console.log(data);
        });
    };
}

angular
    .module('pl')
    .controller('MainController', MainController);