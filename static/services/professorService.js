'use strict'

module.service('ProfessorService', function($http) {
	
	this.save = function(professor){

		return $http({
			method: 'POST',
			url: "http://localhost:8080/professor/save",
			data: professor,
		});
	};
	
	this.list = function(){
		return $http.get('/professor/list', {});
	};
	
	this.delete = function(id){
		
		return $http({
			method: 'POST',
			url: "http://localhost:8080/professor/delete",
			data: id
		});
	};
});