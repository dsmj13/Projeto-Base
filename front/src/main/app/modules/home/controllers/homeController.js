define([ 'app'  ], function(app) {
	app.controller('homeController', ['$filter', '$state','$scope', '$stateParams', '$rootScope',
	                                                  function($filter, $state, $scope, $stateParams, $rootScope){

//		$rootScope.$notification.success("teste de novo");
		$rootScope.$notification.$notify("mario");
	}]);

	return app;
});
