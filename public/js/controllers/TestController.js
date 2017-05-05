function TestController($scope, $http, $state) {
    $scope.translation = '';
    $scope.in_translation = ' ';
    $scope.word = '';
    $scope.hide = false;

    var currIndex = -1;

    $scope.nextWord = function () {
        var length = $scope.words.length;
        if(currIndex < length - 1) {
            currIndex++;
         }
        $scope.in_translation = '';
        $scope.words[currIndex].in_translation = $scope.in_translation;
        $scope.word = $scope.words[currIndex].word;
        $scope.translation = $scope.words[currIndex].translation;
        $scope.translations = $scope.words[currIndex].translations;
        };

    $scope.checkWords = function (value) {
        if (value === $scope.translation){
            /*console.log("true");*/
            $scope.words[currIndex].correctTranslations+=1;
        }
        $scope.words[currIndex].callCount+=1;

            $http.post('/updateWord', $scope.words[currIndex])
                .success(function (data) {
                    console.log(data);
                }).error(function (data) {
                console.log(data);
            });
        $scope.nextWord();
    };

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

    $scope.goHome = function() {
        $state.go('main');
    };
}

angular
    .module('word_js_ap')
    .controller('TestController', TestController);
