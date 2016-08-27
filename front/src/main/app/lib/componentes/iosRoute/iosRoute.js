!function() {
	'use strict';

	var iosRoute = angular.module('iosRoute', ['pascalprecht.translate', 'ui.router','notification']);

	iosRoute.run(['$rootScope', '$state', '$translate', '$translatePartialLoader', '$notificationService',
	              function($rootScope, $state, $translate, $translatePartialLoader, $notificationService){
		$rootScope.reloadState = function(state) {
			$state.go(state, {}, {reload:true});
		};
		
		$rootScope.$on('$stateChangeSuccess', function(event, toState, toParadwlti, fromState, fromParadwlti){

			if(!$translatePartialLoader.isPartAvailable(toState.module)) {
				$translatePartialLoader.addPart(toState.module);
			}
			$notificationService.hideLoading();
		});

		$rootScope.$on('$stateChangeStart', function(event, toState, toParadwlti, fromState, fromParadwlti) {

			if(fromState.module) {
				if($translatePartialLoader.isPartAvailable(fromState.module)) {
					$translatePartialLoader.deletePart(fromState.module, true);
				};
			}
			$notificationService.loading();
		});
	}]);

	iosRoute.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider) {
		iosRoute.$stateProvider = $stateProvider;  
		iosRoute.$urlRouterProvider = $urlRouterProvider;
	}]);

	iosRoute.createRoute = function(routes) {
		try{
			var elem = angular.element('body');
			var injector = elem.injector();
			var myService = injector.get('iosRouteService');
			myService.create(routes);
			elem.scope().$apply();
		}
		catch(e) {
			console.log(e);
		}
	}
	return iosRoute;
}();

!function() {
	'use strict';
	var iosRoute = angular.module('iosRoute');

	iosRoute.factory('iosRouteService', [ '$log', '$http', '$q', '$state', '$rootScope',  
	                                      function($log, $http, $q, $state, $rootScope){

		'use strict';

		var appBaseUrl = (typeof appConfig.appBaseUrl != "undefined") ? appConfig.appBaseUrl : 'app';

		var setState = function(content) {
			angular.forEach(content, function(object, key) {
				try{
					if(object.children) {
						setState(object.children);
					}

					if(object.inner) {stateProvider
						setState(object.inner);
					}

					var tpl = (object.view) ? object.view : object.text.toLowerCase().replace(/\s/g, "-");
					var url = object.text.toLowerCase().replace(/\s/g, "-");
					var state = url;

					var textClean = object.text;
					var tpl = (object.view) ? object.view : object.text.toLowerCase().replace(/\s/g, "-");
					var url = textClean.toLowerCase().replace(/\s/g, "-");

					var state = url;

					var wordsControl = (object.module) ? object.module.split('-') : new Array();
					var secondName = '';
					if(wordsControl.length > 1) {
						for (var i=1;i<wordsControl.length;i++)
						{ 
							secondName += wordsControl[i].charAt(0).toUpperCase() + wordsControl[i].slice(1);
						}
					}
					var control = (wordsControl.length) ? wordsControl[0].toLowerCase() + secondName + 'Controller' : null;
					//console.log(control);
					if(object.state) {
						if(object.state.url) {
							url = object.state.url;
						}
						if(object.state.name) {
							state = object.state.name;
						}
					}

					var bootstrapJs = (object.resolve) ? object.resolve : object.module;
					if(!$state.get(state)) {
						iosRoute.$stateProvider
						.state(state, {
							url: "/" + url ,
							templateUrl: appBaseUrl + '/modules/' + object.module + '/views/' + tpl + '.tpl.html',
							resolve: resolve([  'modules/' + object.module + '/' + bootstrapJs]),
							controller: (object.controller) ? object.controller : control ,
									breadcrumb: object.breadcrumb ? object.breadcrumb : object.text,
											roles: object.roles ? object.roles : false,
													module: object.module
													,onEnter: (object.onEnter) ? object.onEnter() : function(){}
						});
					}
				}
				catch(e) {
					$log.error(e);
				}
			});
		}

		function resolve(names) {
			return {
				load: ['$q', '$rootScope', function ($q, $rootScope) {
					var defer = $q.defer();
					require(names, function () {
						defer.resolve();
						$rootScope.$apply();
					});
					return defer.promise;
				}]
			}
		};

		var create = function(content) {

			var deferred = $q.defer();

			var defaultRoute = '';

			angular.forEach(arguments, function(content) {
				defaultRoute = (typeof appConfig.defaultRoute != "undefined") ? appConfig.defaultRoute : 'home';

				iosRoute.$urlRouterProvider.otherwise(defaultRoute);

				if(typeof content == 'string') {
					$http.get(content).then(function(success) {
						setState(success.data);
						deferred.resolve(success.data);
					}, function(reason){
						console.error('Erro na criação de rotas : ' + reason);
					});
				}
				else {
					setState(content);
					deferred.resolve(content);
				}
			});
			return deferred.promise;
		}
		return {
			create: create
		};
	}]);

	return iosRoute;
}();