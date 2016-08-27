define([], function() {
  var routes = [
                
      {
          module: 'home',
          text: 'Home',
          view: 'home',
          controller : 'homeController',
          state : {
          	name : 'home',
        	  	url : 'home'
          },
//    		roles : [ '*' ]
        }
  ];
  return routes;
});