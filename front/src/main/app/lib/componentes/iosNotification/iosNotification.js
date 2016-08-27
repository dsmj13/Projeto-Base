!function() {
	'use strict';
	try {
		var notification = angular.module('notification', ['ui-notification']);
		/*
		 * Injecting into $rootScope
		 */
		notification.run(['$rootScope', '$notificationService', function($rootScope, $notificationService) {
			$rootScope.$notification = $notificationService;
			$rootScope.notification = {
					messages : []	
			}
		}]);

		return notification;
	}catch(e) {
		$log.error(e);
	}
}();

!function() {
	'use strict';

	var notification = angular.module('notification');

	var template = 
		"<div id='notifications-portlet' ng-repeat='alert in localMessages' class='alert' ng-class='\"alert-\" + (alert.type || \"warning\")'>\n" +
		"    <button type='button' class='close' ng-click='closeMessage($index)'>&times;</button>\n" +
		"    <span>{{alert.message}}</span>\n" +
		"</div>\n";

	notification.directive("notification", ['$rootScope', '$location', '$anchorScroll', '$notificationService', '$compile', '$timeout', 
	                                        function($rootScope, $location, $anchorScroll, $notificationService, $compile, $timeout){
		return{
			restrict:'E',
			replace:true,
			scope: false,
			template: template,
			link: function(scope, element, attrs, ctrl) {
				scope.localMessages = $rootScope.notification.messages;
				$rootScope.notification.messages = [];
			},
			controller : function($scope){

				$scope.$on('_START_ALERT_', function() {
					$scope.localMessages = $rootScope.notification.messages;
					jQuery('html, body').animate({ scrollTop: 0 }, 200);
				});

				$rootScope.$on('CLOSE_MESSAGE', function() {
					$scope.localMessages = [];
				});

				$rootScope
				.$on('$stateChangeStart',
						function (event, toState, toParams, fromState, fromParams) {
					$rootScope.$emit("CLOSE_MESSAGE");
				});
				
				$scope.closeMessage = function(index) {
					$scope.localMessages.splice(index, 1);
				};
			}
		}
	}]);
	/**
	 * não confundir Notification com notification 
	 * Notification é um service externo
	 * notification é o nome do modolo do iosNotification
	 */
	notification.factory('$notificationService', ['$rootScope','Notification', function($rootScope, $notify) {

		var message = [];
		var _container = [];

		var setContainer = function(attrs) {
			_container.push(attrs);
		}

		var setMessage = function($message, $type){
			var message = {};
			message.message = $message;
			message.type = $type;

			$rootScope.notification.messages = [];
			$rootScope.notification.messages.push(message);
			$rootScope.$broadcast('_START_ALERT_');
		}

		return {
			setContainer: setContainer,
			success : function($message) {
				return setMessage($message, 'success');
			},
			info : function($message) {
				return setMessage($message, 'info');
			},
			warning : function($message) {
				return setMessage($message, 'warning');
			},
			error : function($message) {
				return setMessage($message, 'error');
			},
			loading : function($message) {
				$.blockUI({ message: 'Aguarde..' }); 
			},
			hideLoading : function($message) {
				$.unblockUI({ fadeOut: 280 });
			},
			$notify : $notify
		};
	}]);

	return notification;
}();