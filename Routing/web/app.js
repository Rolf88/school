var app = angular.module('viewApp', ['ngRoute']);
// Route Provider Start
app.config(function ($routeProvider) {
  $routeProvider
    .when("/home", {
      templateUrl: "views/home.html",
      controller: "HomeController"
    })
    .when("/products", {
      templateUrl: "views/products.html",
      controller: "ViewController"
    })
    .when("/company", {
      template: "<h2 style='margin:20px;'>General Info about the Company</h2>",
      controller: "CompanyController"
    })
    .when("/blog", {
      template: "<h2 style='margin:20px;'>Our great Company Blog</h2>",
      controller: "BlogController"
    })
    .when("/info/:index", {
      templateUrl: "views/bookdetail.html",
      controller: "ViewController"
    })
    .otherwise({
      redirectTo: "/home"
    })
})
// Route Provider End

// Controllers Start
app.controller("HomeController",function($scope){
  $scope.home = "Home View for this site";
})

app.controller("CompanyController",function($scope){
  $scope.Company = "General info about the company";
})

app.controller("BlogController",function($scope){
  $scope.blog = "Blog for this site";
})

app.controller('ViewController', function ($scope, $routeParams) {
  $scope.products = "Our Products";

  $scope.books = [
    {title: "Become a specialist in Computer Science - Vol 1", info: "Study hard"}
   ,{title: "Become a specialist in Computer Science - Vol 2", info: "Complete all exercises :-)"}
   ,{title: "How to become a specialist in Computer Science - Vol 3",
      info: "Don't play games in the class (before you have completed the exercises).",
      moreInfo: "Remember that V....... :-)"
    }
    , {title: "How to become a specialist in Computer Science - Vol 4",
       info: "Don't drink beers, until Friday (after four)",
       moreInfo: "You know how to check"
    }
  ];

  if (angular.isDefined($routeParams.index)) {
    var i = $routeParams.index;
    $scope.book = $scope.books[i];
  }
});
// Controllers End