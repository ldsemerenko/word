function MainController($scope, $http, $state) {

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

    $scope.startTest = function() {
        $state.go('test');
    };

}

angular
    .module('word_js_ap')
    .controller('MainController', MainController);