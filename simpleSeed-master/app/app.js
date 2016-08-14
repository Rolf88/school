angular.module('myApp', [])
        .filter('phonemail', function () {

            return function (input, option) {
                var icon = option == "mail" ? '\u2709' : '\u260e';
                
                return icon
            }
        })
        .controller('PhoneMailCtrl', ['$scope', function ($scope) {

                var contactinfo = {};
                contactinfo.info = [
                    {id: "1", mail: "hej@hejhej.dk", phone: ""},
                    {id: "2", mail: "hovhov@hov.dk", phone: ""},
                    {id: "3", mail: "", phone: "22558877"}
                ];

//    this.filteredMail = filterFilter(this.info, '@');
                $scope.getInfo = contactinfo.info;
            }]);