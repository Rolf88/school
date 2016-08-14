var myApp = angular.module('DemoApp',[]);

myApp.controller('CarController', ['$scope', function($scope) {
  cars =  [
    {  id: 1, year: 1997,registered: new Date(1999,3,15), make: 'Ford',model: 'E350', description: 'ac, abs, moon', price: 3000 }
    ,{ id: 2, year: 1999,registered: new Date(1996,3,12), make: 'Chevy', model: 'Venture', description: 'None', price: 4900 }
    ,{ id: 3, year: 2000,registered: new Date(199,12,22), make: 'Chevy', model: 'Venture', description: '', price: 5000 }
    ,{ id: 4, year: 1996,registered: new Date(2002,3,15), make: 'Jeep', model: 'Grand Cherokee',description: 'Air, moon roof, loaded',price: 4799 }
  ];

  $scope.cars = cars;
  $scope.title = "Cars Demo App";
  $scope.predicate = "year";
  $scope.nextId = 5;

  $scope.saveCar = function(){
    if($scope.newcar.id == null){
      $scope.newcar.id= $scope.nextId++;
      $scope.cars.push($scope.newcar);
    }
    else{
      for(var i =0; i < $scope.cars.length; i++){
        if($scope.cars[i].id === $scope.newcar.id){
          $scope.cars[i] = $scope.newcar;
          break;
        }
      }
    }
    $scope.newcar ={};
  };

  $scope.deleteCar = function(id){
    for(var i =0; i < $scope.cars.length; i++){
      if($scope.cars[i].id === id){
        $scope.cars.splice(i,1);
        return;
      }
    }
  };

  $scope.editCar = function(id){
    for(var i =0; i < $scope.cars.length; i++){
      if($scope.cars[i].id === id){
        $scope.newcar = angular.copy($scope.cars[i]);
        return;
      }
    }
  };

}]);