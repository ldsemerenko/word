function TestController($scope, $rootScope, $http, $state) {
    $scope.selectLanguage = $rootScope.selectLanguage;
    $scope.selectSorting = $rootScope.selectSorting;
    var currIndex = -1;
    $scope.labels = [5];
    $scope.label = '';
    $scope.counter = 0;
    var i;

    console.log($scope.selectSorting);

    $scope.nextWord = function () {
        var length = $scope.words.length;
        if(currIndex < length - 1) {
            currIndex++;
            } else {
                alert($scope.counter + ' out of ' + length);
                $scope.goHome();
            }

        if($scope.selectLanguage === $rootScope.choseEn){
            $scope.wordArray = $scope.words[currIndex];
            $scope.word = $scope.wordArray[0];
            $scope.label = 'Word: ' + $scope.word.word;
            $scope.translation = $scope.word.translation;
            $scope.wordArray.sort(compareRandom);
            for(i = 0; i < 5; i++){
                $scope.labels[i] = $scope.wordArray[i].translation;
            }
        }else{
            $scope.wordArray = $scope.words[currIndex];
            $scope.word = $scope.wordArray[0];
            $scope.label = 'Слово: ' + $scope.word.translation;
            $scope.translation = $scope.word.word;
            $scope.wordArray.sort(compareRandom);
            for(i = 0; i < 5; i++){
                $scope.labels[i] = $scope.wordArray[i].word;
            }
        }
    };

    $scope.viewWord = function () {
      $scope.viewUnknown();
      $scope.nextWord();
    };

    $scope.viewUnknown = function(){
        if($scope.selectLanguage === $rootScope.choseEn) {
            alert($scope.word.word + ' - ' + $scope.word.translation);
        } else {
            alert($scope.word.translation + ' - ' + $scope.word.word);
        }
    };

    $scope.checkWords = function (value) {
        if (value === $scope.translation){
            $scope.word.correctTranslations+=1;
            $scope.counter++;
        } else {
            $scope.viewUnknown();
        }
        $scope.word.callCount+=1;
        $http.post('/updateWord', $scope.word)
            .success(function (data) {
                // console.log(data);
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
                count: count,
                sorting: $scope.selectSorting
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

    function compareRandom() {
        return Math.random() - 0.5;
    }
}

angular
    .module('word_js_ap')
    .controller('TestController', TestController);
