define(
       [
        'angularAMD',
        'config',
        'angular',
        'restangular',
        'uiRouter',
        'iosRoute',
        'angular-translate',
        'angular-trans-part',
        'blockUi',
        'notification',
        'bootstrap',
        'notify'
        
       ], 
        function(angularAMD, config) {
            'use strict';
            var app = angular.module('webapp', ['restangular',
                                               'iosRoute',
                                               'config'

            ]);
            
            app.run(['configService',
                     function(configService){
            	
            	
            }]);
    
            var createRoute = function(){
            	console.log("CRIADO");
            	
            };
            
            return angularAMD.bootstrap(app);
            
        }
);