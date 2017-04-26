function config($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/main');

    $stateProvider
        .state('main', {
            url: "/main",
            templateUrl: "/assets/views/main.html"
        });
}

angular
    .module('word_js_ap')
    .config(config)
    .run(function ($rootScope, $state) {
        $rootScope.$state = $state;
    });