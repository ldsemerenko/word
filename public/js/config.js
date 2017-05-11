function config($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/main');

    $stateProvider
        .state('main', {
            url: "/main",
            templateUrl: "/assets/views/main.html"
        });

    $stateProvider
        .state('test',{
            url: "/test",
            templateUrl: "/assets/views/test.html"
        });

    $stateProvider
        .state('statistic',{
            url: "/statistic",
            templateUrl: "/assets/views/statistic.html"
        });

}

angular
    .module('word_js_ap')
    .config(config)
    .run(function ($rootScope, $state) {
        $rootScope.$state = $state;
    });
