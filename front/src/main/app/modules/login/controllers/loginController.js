define([ 
        'app'
       
        ], function(app) {
	
        
            app.controller('loginController', [ '$scope' ,'$rootScope','configService',
                                                    function( $scope, $rootScope, configService){
            	
            	$scope.mario = "testeasdasoduiaiosdaiosudaoisdaiosddaouisdauiosd";
            	
            	
            	$rootScope.$notification.success("teste");
            	
            	
            	configService.login.get('teste').then(function(){});
            	
            	
            	
    				
            }]);
               
            return app;
});