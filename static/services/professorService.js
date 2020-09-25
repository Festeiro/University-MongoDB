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
	
	this.delete = function(professor){
		
		return $http({
			method: 'DELETE',
			url: "http://localhost:8080/professor/delete",
			params:{
				professor: professor
			}
		});
	};
});