'use strict'

var module = angular.module('myApp', ['ngMaterial', 'ngAnimate', 'ngSanitize', 'ui.bootstrap']);

module.controller('AppController', function($http, $scope, $window){

	$scope.init = function(){

		$scope.entities = [
			{ name: 'student', display: 'Estudante' },
			{ name: 'department', display: 'Departamento' },
			{ name: 'professor', display: 'Professor' },
			{ name: 'project', display: 'Projeto' }
		]; 
		
		$scope.selectEntity = function(entity){
			
			$scope.selectedEntityName = entity.name;
			$scope.selectedEntityPath = '/templates/' + entity.name + '/' + entity.name + '.html';
		};
		
		$scope.selectEntity($scope.entities[0]);
	};

});
