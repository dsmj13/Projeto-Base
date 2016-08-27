requirejs.config({
	baseUrl:'app',

	paths:{
		'loginRoute'  	: [modulosDir + 'login/routes/loginRoute'],
		'homeRoute'  	: [modulosDir + 'home/routes/homeRoute']
	}
});

(function(){
	var dependencies = [
	                    'angular',
	                    'loginRoute',
	                    'homeRoute'

	                    ]

	define(dependencies, function(angular, login, home){

		var app = angular.module('config',[]);

		app.factory('configService',['$rootScope', 'iosRouteService','Restangular', function($rootScope, iosRouteService, restangular){
			iosRouteService.create(login);
			iosRouteService.create(home);

			/**
			 * CONFIGURA INTERCEPTORS
			 */
			restangular.setErrorInterceptor(function(response, deferred, responseHandler) {

				switch(response.status){
				case 500: 
					break;
				case 401: 
					console.log("401 erro");
					break;
				};
				
				return true;
			});

			/**
			 * TRATA RETORNO DO RESPONSE HTTP
			 */
			restangular.setResponseInterceptor(function(data,
					operation, what, url, response, deferred) {
				var novoResponse = {};

				if (data && operation === 'getList') {
					novoResponse.mensagens = data.mensagens ? data.mensagens : null;
					novoResponse.resultado = data.resultado ? data.resultado : [];
				} else {
					novoResponse = data;
				}
				return novoResponse;
			});

			var resourceUrls = {
					comum :  appConfig.baseUrl + '/api/comum',
					login :  appConfig.baseUrl + '/api/login',
			};

			return {
				comuns : restangular.all(resourceUrls.comum),
				login : restangular.all(resourceUrls.login)
			};

			return app;
		}]);
	});
}());