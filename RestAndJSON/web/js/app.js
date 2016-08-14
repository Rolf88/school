'use strict';

angular.module('myApp', ['ngRoute'])
        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/dataView', {
                    templateUrl: 'dataView.html'
                }).otherwise({redirectTo: 'index.html'});
            }])
        .factory('DataFactory', ["$http", function ($http) {
                return {
                    search: function (amount, properties) {
                        return $http({
                            method: "GET",
                            url: "api/adresses/" + amount + "/" + properties
                        });
                    }
                };
            }])
        .controller('dataViewCtrl', ['DataFactory', function (DataFactory) {
                var self = this;
                self.amount = 25;
                self.properties = "fname,lname,street,city";
                self.fname = "";
                self.lname = "";
                self.street = "";
                self.city = "";
                self.noresult = false;
                self.finalRes = [];

                self.searchData = function () {

                    DataFactory.search(self.amount, self.properties).then(function (response) {
                        self.noresult = false;
                        self.testDatas = response.data;
                    }, function (error) {
                        //self.finalRes = [];
                        self.noresult = true;
                    }
                    );

                };

            }]);