requirejs.config({
	baseUrl:'app',
    paths: {
        /**
         * Externals
         */
        'jQuery'                : [bibliotecasDir + 'jquery/jquery-1.11.3.min'],
        'angular'               : [bibliotecasDir + 'angularjs/angular.min'],
        'angularAMD'            : [bibliotecasDir + 'angularAMD/angularAMD'],
        'restangular'           : [bibliotecasDir + 'restangular/restangular'],
        'domReady'              : [bibliotecasDir + 'requirejs-domready/domReady'],
        'lodash'                : [bibliotecasDir + 'lo-dash/lodash.min'],
        'uiRouter'              : [bibliotecasDir + 'uiRouter/angular-ui-router.min'],
        'angular-translate'     : [bibliotecasDir + 'angular-translate/angular-translate.min'],
        'angular-trans-part'    : [bibliotecasDir + 'angular-translate-loader-partial/angular-translate-loader-partial.min'],
        'underscore'            : [bibliotecasDir + 'underscore/underscore.min'],
        'blockUi'            	: [bibliotecasDir + 'blockUi/blockUi'],
        'bootstrap'            	: [bibliotecasDir + 'bootstrap/js/bootstrap.min'],
        'notify'            	: [bibliotecasDir + 'notify/angular-ui-notification.min'],
        
        /**
         * Internals
         */
        
        'iosRoute'              : [componentesDir + 'iosRoute/iosRoute'],
        'notification'          : [componentesDir + 'iosNotification/iosNotification'],

    },
    shim: {
        'jQuery'                : { exports: 'jQuery' },
        'bootstrap'				: {deps: ['jQuery'], exports: 'bootstrap'},
        'notify'				: { deps: ['bootstrap'], exports: 'notify'},
        'angular'               : { deps: ['jQuery'], exports: 'angular'},	
        'blockUi'               : { deps: ['jQuery'], exports: 'blockUi'},	
        'underscore'            : { deps: ['jQuery'], exports: 'underscore'},	
        'angularAMD'            : { deps: ['angular'], exports: 'angularAMD'},	
        'notification'          : { deps: ['angular', 'iosRoute'], exports: 'notification'},	
        'restangular'           : { deps: ['angular', 'lodash'], exports: 'restangular'},
        'lodash'                : {exports: 'lodash'},
        'uiRouter'				: {deps: ['angular'], exports: 'uiRouter'},
        'iosRoute'				: {deps: ['uiRouter'], exports: 'iosRoute'},
        'angular-translate'		: {deps: ['angular'], exports: 'angular-translate'},
        'angular-trans-part'	: {deps: ['angular', 'angular-translate'], exports: 'angular-trans-part'}
    }
});
