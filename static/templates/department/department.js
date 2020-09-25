'use strict'

module.controller('DepartmentController', function($http, $scope, $window, DepartmentService, ProfessorService){

	$scope.departments = [{}];
	$scope.department = {};
	$scope.professors = [{}];
	$scope.isUpdating = false;
	
	$scope.cols = [
		{name: 'dep_number', flex: '30', display: 'Num. Departamento' },
		{name: 'name', flex: '30', display: 'Nome' },
		{name: 'centralOffice', flex: '10', display: 'Escritorio Principal' },
		{name: 'profLeaderRegAndName', flex: '10', display: 'Professor Lider' },
	];
	
	$scope.listDepartments = function(){

		DepartmentService.list().then(function(response){
		
			$scope.departments = response.data;
			angular.forEach($scope.departments, function(department){
				if(department.profLeader && department.profLeader.reg_number){
					
					department.profLeaderRegAndName = department.profLeader.reg_number.toString() + ' - ' + department.profLeader.name; 
				}
			});
		});
	};
	
	$scope.listDepartments();
	
	$scope.cancel = function(){
		$scope.department = {};
		$scope.isUpdating = false;
	};
	
	$scope.save = function(){
		
		DepartmentService.save($scope.department).then(function(response){			
			$scope.listDepartments();
			$scope.cancel();
		},function(http, status){
			console.log()
			$window.alert("n deu boa" + status);
		});
	};
	
	$scope.update = function(department){
		
		$scope.isUpdating = true;
		$scope.department = angular.copy(department);
	};
	
	$scope.delete = function(department){
		
		DepartmentService.delete(department.dep_number).then(function(response){			
			$scope.listDepartments();
		},function(http, status){
			console.log()
			$window.alert("n deu boa" + status);
		});
	};
	
	ProfessorService.list().then(function(response){
		
		$scope.professors = response.data;
	});
	
});