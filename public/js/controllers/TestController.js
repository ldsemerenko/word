function TestController($scope, $http, $state) {
    $scope.translation = '';
    $scope.in_translation = '';
    $scope.word = '';
    var currIndex = -1;

    $scope.check = function(){

    };

    $scope.nextWord = function () {
        var length = $scope.words.length;
        if(currIndex < length - 1)
            currIndex++;

        $scope.word = $scope.words[currIndex].word;
        $scope.translation = $scope.words[currIndex].translation;
    };
    $scope.prevWord = function () {
        if(currIndex > 0)
            currIndex--;

        $scope.word = $scope.words[currIndex].word;
        $scope.translation = $scope.words[currIndex].translation;

    };

    // $scope.getRandomWords = function () {
        var count = 20;
        $http({
            url: '/getRandomWords',
            method: 'GET',
            params: {
                count: count
            }
        }).success(function (data) {
            $scope.words = data;
            $scope.nextWord();
            console.log($scope.words);
        }).error(function (data) {
            console.log(data);
        });
    // };


}

angular
    .module('word_js_ap')
    .controller('TestController', TestController);
