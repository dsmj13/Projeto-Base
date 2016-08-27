var appConfig = {
		servidor: '/${contexto.frontend}/',
		versao_app: '${project.version}',

		ambiente: {
			isNotMinify : '${minify.skip}'
		},
		baseUrl :  '${contexto.backend}' ,
		defaultRoute : 'login',
		login: {
			url: '/${contexto.backend}'  + '/oauth/token',//'login.json',
			url_usuario: '/${contexto.backend}'  +  '/api/login/dadosUsuarioLogado',// 'usuario.json',
			sucesso: 'manterServidor',
			limite: 30
		},
		logout: {
			url: '/${contexto.backend}/api/login/logout'//logout.json
		}
};

var libDir =  'lib/';
var apiDir = appConfig.servidor + '/vendor/angular/api/' + appConfig.versao_arquitetura + '/';
var componentesDir = libDir + 'componentes/';
var bibliotecasDir =  libDir + 'vendor/';
var modulosDir = 'modules/';

requirejs.config({
	baseUrl:'app',
	paths: {
		'app'		: [ 'app'],
		'config'	: [ 'config'],
		'init'		: [libDir + 'init']
	},
	shim: {
		'app': {deps: ['init'], exports: 'app'},
		'config': {deps: ['init'], exports: 'config' }
	}, deps: ['app']
});

var _createRoute = function(rotas){
	
	
	var isMinify = appConfig.ambiente.isNotMinify == 'true';

	if(!isMinify) {
		sufixo = '.min';
		rotasMin = [];
		angular.forEach(rotas, function(value, key) {
			rotasMin.push(value + sufixo);
		});
		return rotasMin;
	}else
		return rotas;
};