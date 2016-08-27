define(['app'], 
		function(app){

	app.factory("manterCursoService", ['$http','$q', 'ResourcesService', '$rootScope', '$translatePartialLoader',
	                                         function($http, $q, ResourcesService, $rootScope, $translatePartialLoader){

		var pesquisar = function(parans){
			return ResourcesService.curso.getList(parans);
		};

		var salvar = function(inst){
			return ResourcesService.curso.customPOST(inst);
		};
		
		var pesquisarNomes = function(value){
			return ResourcesService.curso.one("nome-curso").getList("?value=" + value);
		};
		
		var excluir = function(id){
			return ResourcesService.curso.customDELETE(id);
		};

		return {
			pesquisar: pesquisar,
			salvar: salvar,
			pesquisarNomes: pesquisarNomes,
			excluir: excluir
		};
	}]);

	return app;

});