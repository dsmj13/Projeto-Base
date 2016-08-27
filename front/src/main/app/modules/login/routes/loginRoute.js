define([], function() {
	   
	var routes = [                 		
       		{
       			module: 'login',
                  text: '',
                  controller:'loginController',
                  view: 'login', 
                  state : {
                  	name : 'login',
                  	url: 'login'
                  }
       		}];
	    
	   return routes;
	});