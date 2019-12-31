var app = angular.module('crudApp',['ui.router','ngStorage']);

app.constant('urls', {
    //BASE: 'http://ec2-52-77-233-254.ap-southeast-1.compute.amazonaws.com:8070/student-web',
    //USER_SERVICE_API : 'http://ec2-52-77-233-254.ap-southeast-1.compute.amazonaws.com:8070/student-web/api/student/'
    BASE: 'http://localhost:8070/student-web',
    USER_SERVICE_API : 'http://localhost:8070/student-web/api/student/'
});

app.config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('home', {
                url: '/',
                templateUrl: 'partials/list',
                controller:'StudentController',
                controllerAs:'ctrl',
                resolve: {
                    students: function ($q, StudentService) {
                        console.log('Load all students');
                        var deferred = $q.defer();
                        StudentService.loadAllStudents().then(deferred.resolve, deferred.resolve);
                        return deferred.promise;
                    }
                }
            });
        $urlRouterProvider.otherwise('/');
    }]);

