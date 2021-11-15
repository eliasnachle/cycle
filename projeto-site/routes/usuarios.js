var express = require('express');
var router = express.Router();
var sequelize = require('../models').sequelize;
var Usuario = require('../models').Usuario;

var Planos = require('../models').Planos;
var UsuariosSuporte = require('../models').UsuariosSuporte;
var UsuariosContratante = require('../models').UsuariosContratante;

let sessoes = [];

/* Validar login Gerente/Contratante */
router.post('/login-contratante', function (req, res, next) {
	console.log('Fazendo login gerente');

	var email = req.body.email;
	var password = req.body.password;

	let instrucaoSql = `select * from tblUsuariosContratante where emailContratante='${email}' and senhaContratante='${password}'`;
	console.log(instrucaoSql);

	sequelize.query(instrucaoSql, {
		model: UsuariosContratante
	}).then(resultado => {
		console.log(`Encontrados: ${resultado.length}`);
		console.log(`Conteudo completo: ${resultado}`)

		if (resultado.length == 1) {
			sessoes.push(resultado[0].dataValues.nomeContratante);
			console.log('sessoes: ', sessoes);
			res.json(resultado[0]);
		} else if (resultado.length == 0) {
			res.status(403).send('Login e/ou senha inválido(s)');
		} else if(resultado.length >= 2){
			res.status(409).send('Mais de um usuário com o mesmo login e senha!');
		}

	}).catch(erro => {
		console.error(erro);
		res.status(500).send(erro.message);
	});
});

/* Validar login suporte */
router.post('/login-suporte', function (req, res, next) {
	console.log('Fazendo login suporte');

	var email = req.body.email;
	var password = req.body.password;

	let instrucaoSql = `select * from tblUsuariosSuporte where emailSuporte='${email}' and senhaSuporte='${password}'`;
	console.log(instrucaoSql);

	sequelize.query(instrucaoSql, {
		model: UsuariosSuporte
	}).then(resultado => {
		console.log(`Encontrados: ${resultado.length}`);
		console.log(`Conteudo completo: ${resultado}`)

		if (resultado.length == 1) {
			sessoes.push(resultado[0].dataValues.nomeSuporte);
			console.log('sessoes: ', sessoes);
			res.json(resultado[0]);
		} else if (resultado.length == 0) {
			res.status(403).send('Login e/ou senha inválido(s)');
		} else if(resultado.length >= 2){
			res.status(409).send('Mais de um usuário com o mesmo login e senha!');
		}

	}).catch(erro => {
		console.error(erro);
		res.status(500).send(erro.message);
	});
});

/* Cadastrar usuário contratante com validação de email */
router.post('/register-contratante-validation', function (req, res, next) {
	console.log('Criando um usuário');

	var email = req.body.email;

	let instrucaoSql = `select * from UsuariosContratante where emailContratante='${email}'`;
	console.log(instrucaoSql);

	sequelize.query(instrucaoSql, {
		model: UsuariosContratante
	}).then(resultado => {
		console.log(`Encontrados: ${resultado.length}`);

		if (resultado.length == 1) {
			res.status(409).send(`O email:${email} que tentou ser cadastrado, ja está cadastrado no nosso banco`);
		} else {
			UsuariosContratante.create({
				nomeContratante: req.body.nome,
				emailContratante: req.body.email,
				senhaContratante: req.body.senha,
				cpfContratante: req.body.cpf,
				keyContratante: req.body.keyContratante,
				idPlano: req.body.idPlano
			}).then(resultado => {
				console.log(`Registro criado: ${resultado}`)
				res.send(resultado);
			}).catch(erro => {
				console.log("ERRO: NÂO FOI POSSIVEL MANDAR PARA O BANCO")
				console.error(erro);
				res.status(500).send(erro.message);
			});
		}
	}).catch(erro => {
		console.log("ERRO: EMAIL JA CADASTRADO")
		console.error(erro);
		res.status(500).send(erro.message);
	});
});

/* Cadastro simples de ususario contratante*/
router.post('/register-contratante/:key', function (req, res, next) {
	console.log('Registrando novo gerente');

	var idPlanoFkNum;

	if(req.body.idPlano == "free") {
		idPlanoFkNum = 1;
	}else if(req.body.idPlano == "basic") {
		idPlanoFkNum = 2;
	}else {
		idPlanoFkNum = 3;
	}

	UsuariosContratante.create({
		nomeContratante: req.body.nome,
		emailContratante: req.body.email,
		senhaContratante: req.body.senha,
		cpfContratante: req.body.cpf,
		keyContratante: req.params.key,
		idPlano: idPlanoFkNum
	}).then(resultado => {
		console.log(`Registro criado: ${resultado}`)
		res.status(201).send(resultado);
	}).catch(erro => {
		console.error(erro);
		res.status(500).send(erro.message);
	});

});

/* Cadastro simples de ususario suporte*/
router.post('/register-suporte/:idContratante', function (req, res, next) {
	console.log('Registrando novo gerente');

	UsuariosSuporte.create({
		nomeSuporte: req.body.nome,
		emailSuporte: req.body.email,
		senhaSuporte: req.body.senha,
		idPlano: req.params.idContratante
	}).then(resultado => {
		console.log(`Registro criado: ${resultado}`)
		res.status(201).send(resultado);
	}).catch(erro => {
		console.error(erro);
		res.status(500).send(erro.message);
	});

});

/* Deletando usuario suporte pelo seu id */
router.delete('/delete-suporte/:idSuporte', function (req, res, next) {

	var instrucaoSql = `DELETE tblUsuariosSuporte WHERE idUsuarioSuporte = ${req.params.idSuporte}`

	sequelize.query(instrucaoSql, {
		model: UsuariosSuporte
	}).then(resultado => {
		if (resultado.length == 1) {
			res.status(204).send('O usuario suporte foi deletado com sucesso');
		} else if (resultado.length == 0) {
			res.status(404).send('Não foi possivel deletar o usuario, nem um usuario encontrado');
		}
	}).catch(erro => {
		console.error(erro);
		res.status(500).send(erro.message);
	});

});

/* Verificação de usuário */
router.get('/sessao/:login', function (req, res, next) {
	let login = req.params.login;
	console.log(`Verificando se o usuário ${login} tem sessão`);

	let tem_sessao = false;
	for (let u = 0; u < sessoes.length; u++) {
		if (sessoes[u] == login) {
			tem_sessao = true;
			break;
		}
	}

	if (tem_sessao) {
		let mensagem = `Usuário ${login} possui sessão ativa!`;
		console.log(mensagem);
		res.send(mensagem);
	} else {
		res.sendStatus(403);
	}

});


/* Logoff de usuário */
router.get('/sair/:login', function (req, res, next) {
	let login = req.params.login;
	console.log(`Finalizando a sessão do usuário ${login}`);
	let nova_sessoes = []
	for (let u = 0; u < sessoes.length; u++) {
		if (sessoes[u] != login) {
			nova_sessoes.push(sessoes[u]);
		}
	}
	sessoes = nova_sessoes;
	res.send(`Sessão do usuário ${login} finalizada com sucesso!`);
});


/* Recuperar todos os usuários */
router.get('/', function (req, res, next) {
	console.log('Recuperando todos os usuários');
	Usuario.findAndCountAll().then(resultado => {
		console.log(`${resultado.count} registros`);

		res.json(resultado.rows);
	}).catch(erro => {
		console.error(erro);
		res.status(500).send(erro.message);
	});
});

module.exports = router;
