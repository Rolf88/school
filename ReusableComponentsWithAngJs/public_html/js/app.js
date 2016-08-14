'use strict';
var app = angular.module('myApp', ['ngRoute']);

app.filter('name', function () {
    return function (person) {
        var result = person.lastName + " " + person.firstName;

        return result;
    };
});

app.controller('myCtrl', ['fact', function (fact) {
        var self = this;

        //login
        self.persons = fact.allPersons();
        self.users = self.persons;
        self.person;
        self.error = "";
        self.isAuthenticated = false;
        self.goneWrong = true;
        self.login = function () {
            for (var i = 0, max = self.users.length; i < max; i++) {
                if (self.users[i].firstName.localeCompare(self.user.firstName) == 0 && self.users[i].lastName.localeCompare(self.user.lastName) == 0) {
                    self.isAuthenticated = true;
                    self.goneWrong = true;
                    self.person = self.user;
                    break;
                } else {
                    self.isAuthenticated = false;
                    self.goneWrong = false;
                    self.error = "Login Gone Wrong";
                }
            }
        };

        //converters
        self.testString = "my example service";
        self.camelCase = fact.camelCase(self.testString);
        self.titleCase = fact.titleCase(self.testString);
        self.dashCase = fact.dashCase(self.testString);
    }]);

app.factory('fact', function () {
    return{
        allPersons: function () {
            var persons = [];
            persons.push({firstName: 'Peter', lastName: 'Smith'});
            persons.push({firstName: 'Hans', lastName: 'Hansi'});
            return persons;
        },
        camelCase: function (str) {
            return str.replace(/\w\S*/g, function (txt) {
                return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();
            }).replace(/\s/g, '');
        },
        titleCase: function (str) {
            return str.replace(/\w\S*/g, function (txt) {
                return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();
            });
        },
        dashCase: function (str) {
            return str.replace(/\s+/g, '-').toLowerCase();
        }
    };
});
app.directive('loginForm', function () {
    return {
        restrict: 'AEC',
        templateUrl: 'loginForm.html'
    };
});