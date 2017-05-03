function TestController($scope, $http) {
    $scope.translation = '';
    $scope.in_translation = ' ';
    $scope.word = '1';
    var currIndex = -1;

    $scope.nextWord = function () {
        var length = $scope.words.length;
        if(currIndex < length - 1) {
            currIndex++;
            console.log(currIndex+1 + '/' + length);
            console.log($scope.words[currIndex].word);

        }
        $scope.in_translation = '';
        $scope.words[currIndex].in_translation = $scope.in_translation;
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
