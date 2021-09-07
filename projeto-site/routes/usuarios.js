var express = require('express');
var router = express.Router();
var sequelize = require('../models').sequelize;
var tbEmpresa = require('../models').tbEmpresa;
var tbGestor = require('../models').tbGestor;
var tbFuncionario = require('../models').tbFuncionario;

let sessoes = [];

/* Recuperar usuário por login e senha */
router.post('/autenticar', function(req, res, next) {
	console.log('Recuperando usuário por login e senha');

	var emailEmpresa = req.body.emailEmpresa; // depois de .body, use o nome (name) do campo em seu formulário de login
	var senhaEmpresa = req.body.senhaEmpresa; // depois de .body, use o nome (name) do campo em seu formulário de login	
	
	let instrucaoSql = `select * from tbEmpresa where emailEmpresa='${emailEmpresa}' and senhaEmpresa='${senhaEmpresa}'`;
	console.log(instrucaoSql);

	sequelize.query(instrucaoSql, {
		model: tbEmpresa
	}).then(resultado => {
		console.log(`Encontrados: ${resultado.length}`);

		if (resultado.length == 1) {
			sessoes.push(resultado[0].dataValues.login);
			console.log('sessoes: ',sessoes);
			res.json(resultado[0]);
		} else if (resultado.length == 0) {
			res.status(403).send('Login e/ou senha inválido(s)');
		} else {
			res.status(403).send('Mais de um usuário com o mesmo login e senha!');
		}

	}).catch(erro => {
		console.error(erro);
		res.status(500).send(erro.message);
  	});
});

router.post('/autenticarGestor', function(req, res, next) {
	console.log('Recuperando gestor por login e senha');

	var emailGestor = req.body.emailGestor; // depois de .body, use o nome (name) do campo em seu formulário de login
	var senhaGestor = req.body.senhaGestor; // depois de .body, use o nome (name) do campo em seu formulário de login	
	
	let instrucaoSql = `select * from tbGestor
		inner join tbEmpresa on fk_tbGestor_tbEmpresa = idtbEmpresa 
		where emailGestor = '${emailGestor}' and senhaGestor = '${senhaGestor}';`;
	console.log(instrucaoSql);

	sequelize.query(instrucaoSql, {
		model: tbEmpresa
	}).then(resultado => {
		console.log(`Encontrados: ${resultado.length}`);

		if (resultado.length == 1) {
			sessoes.push(resultado[0].dataValues.login);
			console.log('sessoes: ',sessoes);
			res.json(resultado[0]);
		} else if (resultado.length == 0) {
			res.status(403).send('Login e/ou senha inválido(s)');
		} else {
			res.status(403).send('Mais de um usuário com o mesmo login e senha!');
		}

	}).catch(erro => {
		console.error(erro);
		res.status(500).send(erro.message);
  	});
});

/* PEGANDO TODOS OS USUARIOS */
router.get('/gestor/:id', function(req, res, next) {
	console.log('Recuperando todos os usuários');

	tbFuncionario.findAndCountAll({
		where: {
			fk_tbFuncionario_tbGestor: req.params.id
		}
	}).then(resultado => {
		res.json(resultado.rows);
	}).catch(erro => {
		console.error(erro);
		res.status(500).send(erro.message);
  	});
});


/* Cadastrar usuário */
router.post('/cadastrar', function(req, res, next) {
	console.log('Criando um usuário');
	
	tbEmpresa.create({
		cnpj: req.body.cnpj,
		razaoSocial: req.body.razaoSocial,
		emailEmpresa: req.body.emailEmpresa,
		senhaEmpresa: req.body.senhaEmpresa,
		telefone: req.body.telefone,
		uf: req.body.uf,
		cep: req.body.cep,
		bairro: req.body.bairro,
		logradouro: req.body.logradouro,
		complemento: req.body.complemento
	}).then(resultado => {
		console.log(`Registro criado: ${resultado}`)
        res.send(resultado);
    }).catch(erro => {
		console.error(erro);
		res.status(500).send(erro.message);
  	});
});

router.post('/cadastrarGestor/:id', function(req, res, next) {
	console.log('Criando um Gestor');

	let instrucaoSql = `INSERT INTO tbGestor (nomeGestor, emailGestor, senhaGestor, fk_tbGestor_tbEmpresa)
	values ('${req.body.nomeGestor}', '${req.body.emailGestor}', '${req.body.senhaGestor}', ${req.params.id})`

	sequelize.query(instrucaoSql, {
		model: tbGestor
	}).then(resultado => {
		console.log(`Registro criado: ${resultado}`)
        res.send(resultado);
    }).catch(erro => {
		console.error(erro);
		res.status(500).send(erro.message);
  	});
});


/* Verificação de usuário */
router.get('/sessao/:login', function(req, res, next) {
	let login = req.params.login;
	console.log(`Verificando se o usuário ${login} tem sessão`);
	
	let tem_sessao = false;
	for (let u=0; u<sessoes.length; u++) {
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
router.get('/sair/:login', function(req, res, next) {
	let login = req.params.login;
	console.log(`Finalizando a sessão do usuário ${login}`);
	let nova_sessoes = []
	for (let u=0; u<sessoes.length; u++) {
		if (sessoes[u] != login) {
			nova_sessoes.push(sessoes[u]);
		}
	}
	sessoes = nova_sessoes;
	res.send(`Sessão do usuário ${login} finalizada com sucesso!`);
});

router.get('/empresa/:id', function(req, res, next) {
	Empresa.findByPk(req.params.id).then(response => {
		console.log('Recuperando dados da empresa')
		res.json(response);
	}).catch(error => {
		console.error(erro);
		res.status(500).send(erro.message);
	})
})


/* Recuperar todos os usuários */
router.get('/', function(req, res, next) {
	console.log('Recuperando todos os usuários');
	tbGestor.findAndCountAll().then(resultado => {
		console.log(`${resultado.count} registros`);

		res.json(resultado.rows);
	}).catch(erro => {
		console.error(erro);
		res.status(500).send(erro.message);
  	});
});



// ----- ROTAS DOS FUNCIONARIOS -----

router.post('/cadastrarFuncionario/:id', function(req, res, next) {
	console.log('Criando um Funcionario');

	let instrucaoSql = `insert into tbFuncionario(nomeFuncionario, emailFuncionario, usuarioFuncionario, senhaFuncionario, fk_tbFuncionario_tbGestor)
	values 
	('${req.body.nomeFuncionario}', '${req.body.emailFuncionario}', '${req.body.usuarioFuncionario}',
	'${req.body.senhaFuncionario}', ${req.params.id});`

	sequelize.query(instrucaoSql, {
		model: tbFuncionario
	}).then(resultado => {
		console.log(`Registro criado: ${resultado}`)
        res.send(resultado);
    }).catch(erro => {
		console.error(erro);
		res.status(500).send(erro.message);
  	});
});

router.get('/funcionario/:id', function(req, res, next) {
	console.log('Recuperando todos os usuários');

	tbFuncionario.findAndCountAll({
		where: {
			fk_tbFuncionario_tbGestor: req.params.id
		}
	}).then(resultado => {
		res.json(resultado.rows);
	}).catch(erro => {
		console.error(erro);
		res.status(500).send(erro.message);
  	});
});

router.delete('/delete/:id', function (req, res, next) {
	console.log('Excluindo um funcionario');

	let instrucaoSql = `DELETE FROM tbFuncionario WHERE idtbFuncionario = ${req.params.id}`;

	sequelize.query(instrucaoSql, {
		model: tbFuncionario
	}).then(resultado => {
		res.send(resultado);
	}).catch(erro => {
		console.error(erro);
		res.status(500).send(erro.message);
	});
})


module.exports = router;
