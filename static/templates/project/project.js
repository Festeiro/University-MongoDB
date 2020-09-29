'use strict'

module.controller('ProjectController', function($filter, $http, $scope, $window, ProjectService, ProfessorService, ParticipatesService){
	
	$scope.projects = [{}];
	$scope.project = {};
	$scope.isUpdating = false;

	$scope.cols = [
		{name: 'projectNumber', flex: '30', display: 'Numero' },
		{name: 'financier', flex: '30', display: 'Financiador' },
		{name: 'budget', flex: '20', display: 'Orcamento'},
		{name: 'startDate', flex: '10', isDate: true, display: 'Dt. Inicial' },
		{name: 'endDate', flex: '20', isDate: true, display: 'Dt. Final' },
		{name: 'profLeaderRegAndName', flex: '10', display: 'Prof. Gerente'}
	];
	
	$scope.listProjects = function(){
				
		ProjectService.list().then(function(response){
		
			angular.forEach(response.data, function(project){
				project.startDate = new Date(project.startDate);				
				project.endDate = new Date(project.endDate);				
				if(project.profLeader && project.profLeader.reg_number){
					project.profLeaderRegAndName = project.profLeader.reg_number.toString() + ' - ' + project.profLeader.name; 
				}
			});
			$scope.projects = response.data;
		});
	};
	
	$scope.listProjects();
	
	$scope.cancel = function(){
		$scope.project = {};
		$scope.isUpdating = false;
	}
	
	$scope.save = function(){
		
		ProjectService.save($scope.project).then(function(response){			
			$scope.listProjects();
			$scope.cancel();
		},function(http, status){
			console.log()
			$window.alert("n deu boa" + status);
		});
	};
	
	$scope.update = function(project){
		
		$scope.isUpdating = true;
		$scope.project = angular.copy(project);
		resetParticipates();
		$scope.listParticipates();
	};
	
	$scope.delete = function(project){
		
		ProjectService.delete(project.projectNumber).then(function(response){			
			$scope.listProjects();
			if(response.data == false){
				$window.alert("Existe alguma relacao" + status);
			}
			$scope.cancel();
		},function(http, status){
			$window.alert("n deu boa" + status);
		});
	};
	
	// professors
	$scope.professors = [{}];
	ProfessorService.list().then(function(response){
		
		$scope.professors = response.data;
	});
	
	// participates
	$scope.participatess = [];
	$scope.participates = {};
	
	$scope.participatesCols = [
		{name: 'professorRegAndName', flex: '20', display: 'Reg. Professor' },
	];
	
	var resetParticipates = function(){
		$scope.participates = { project: $scope.project };
	};
	
	$scope.listParticipates = function(){

		ParticipatesService.listByProjectNumber($scope.participates.project.projectNumber).then(function(response){
		
			$scope.participatess = response.data;
			angular.forEach($scope.participatess, function(participates){
				if(participates.professor && participates.professor.reg_number){
					
					participates.professorRegAndName = participates.professor.reg_number.toString() + ' - ' + participates.professor.name; 
				}
			});
		});
	};
		
	$scope.saveParticipates = function(){
		
		ParticipatesService.save($scope.participates).then(function(response){			
			$scope.listParticipates();
			resetParticipates();
		},function(http, status){
			console.log()
			$window.alert("n deu boa" + status);
		});
	};
	
	$scope.deleteParticipates = function(participates){
		
		ParticipatesService.delete(participates.project.projectNumber, participates.professor.reg_number).then(function(response){			
			$scope.listParticipates();
		},function(http, status){
			console.log()
			$window.alert("n deu boa" + status);
		});
	};
});