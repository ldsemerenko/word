function config($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/welcome');

    $stateProvider
        .state('welcome', {
            url: "/welcome",
            templateUrl: "/assets/views/welcome.html",
            controller: "MainController"
        })
        .state('home', {
            url: "/home",
            templateUrl: "/assets/views/home.html",
            controller: "MainController"
        });
}

angular
    .module('pl')
    .config(config)
    .run(function ($rootScope, $state) {
        $rootScope.$state = $state;
    });