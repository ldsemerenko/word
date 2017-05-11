function MainController($rootScope, $scope, $http, $state) {
    $rootScope.choseEn = 'en->ru';
    $rootScope.choseRu = 'ru->en';
    $scope.testTypes = [$rootScope.choseEn, $rootScope.choseRu];
    $scope.selectLanguage = $scope.testTypes[0];

    $scope.uploadWords = function(){
        $http({
            url: '/uploadWords',
            method: 'GET'

        }).success(function (data) {
            console.log(data);
        }).error(function (data) {
            console.log(data);
        });

    };

    $scope.downloadWords = function(){
        $http({
            url: '/downloadWords',
            method: 'GET'

        }).success(function (data) {
            console.log(data);
        }).error(function (data) {
            console.log(data);
        });

    };

    $scope.addWord = function () {
        var word = {
            word: $scope.word,
            translation: $scope.translation
        };
        $http.post('/addWord', word)
            .success(function (data) {
                // console.log(data);
                if(data === 'Word added'){
                    $scope.word = '';
                    $scope.translation = '';
                } else {
                    alert($scope.word + " уже есть в словаре");
                }
            }).error(function (data) {
                console.log(data);
            });

    };

    $scope.startTest = function() {
        $rootScope.selectLanguage = $scope.selectLanguage;
        $state.go('test');
    };

    $scope.viewStatistic = function () {
        $state.go('statistic');
    }

}

angular
    .module('word_js_ap')
    .controller('MainController', MainController);