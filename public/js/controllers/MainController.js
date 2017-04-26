function MainController($scope, $http) {
    $scope.addWord = function () {
        var word = {
            word: $scope.word,
            translation: $scope.translation
        };
        $http.post('/addWord', word)
            .success(function (data) {
                console.log(data);
            }).error(function (data) {
                console.log(data);
            });
    };

    $scope.getRandomWords = function () {
        var count = 20;
        $http({
            url: '/getRandomWords',
            method: 'GET',
            params: {
                count: count
            }
        }).success(function (data) {
            console.log(data);
        }).error(function (data) {
            console.log(data);
        });
    }
}

angular
    .module('word_js_ap')
    .controller('MainController', MainController);