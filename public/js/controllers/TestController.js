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
            /*console.log(currIndex+1 + '/' + length);*/
            
        }
        $scope.in_translation = '';
        $scope.words[currIndex].in_translation = $scope.in_translation;
        $scope.word = $scope.words[currIndex].word;
        $scope.translation = $scope.words[currIndex].translation;
        $scope.translations = $scope.words[currIndex].translations;
        };
    $scope.prevWord = function () {
        if(currIndex > 0)
            currIndex--;

        $scope.word = $scope.words[currIndex].word;
        $scope.translation = $scope.words[currIndex].translation;

    };

    $scope.checkWords = function (value) {
        if (value === $scope.translation){
            /*console.log("true");*/
            $scope.words[currIndex].correctTranslations = 1;
        } else{
            $scope.words[currIndex].correctTranslations = 0;
        }

        if(currIndex+1 === $scope.words.length) {
            /*в конце отправка слов на обновление счетчиков в базе*/
            $http.post('/updateWords', $scope.words)
                .success(function (data) {
                    console.log(data);
                }).error(function (data) {
                console.log(data);
            });
        }

        $scope.nextWord();
    };

    $scope.updateWord = function () {
        var word = {


        };
        $http.post('/addWord', word)
            .success(function (data) {
                console.log(data);
                if(data=='Word added'){
                    $scope.word = '';
                    $scope.translation = '';
                }
            }).error(function (data) {
            console.log(data);
        });
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
