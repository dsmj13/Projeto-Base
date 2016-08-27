define(['app'], 
        function(app){
	
	app.factory("loginService", ['$http','$q', 'ResourcesService', '$rootScope', '$translatePartialLoader',
					 			function($http, $q, ResourcesService, $rootScope, $translatePartialLoader){
		
		//I18n
		$translatePartialLoader.addPart('login');
		
		var selecionarPerfil = function(perfil) {
		  return ResourcesService.login.customPOST(perfil, 'selecionarPerfil');
		};
		
		return {
			selecionarPerfil : selecionarPerfil
		};
	}]);
	
	return app;
		
});