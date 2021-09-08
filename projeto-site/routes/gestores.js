var express = require('express');
var router = express.Router();
var sequelize = require('../models').sequelize;
var tbGestor = require('../models').tbGestor;
var tbFuncionario = require('../models').tbFuncionario;
var tbRepositorioGitHub = require('../models').tbRepositorioGitHub;

router.post('/cadastrar/:id', function (req, res, next) {
	console.log('Criando um gestor');

	let instrucaoSql = `INSERT INTO tbGestor(cpf, nomeGestor, emailGestor, usuarioGestor,  senhaGestor, fk_tbGestor_tbEmpresa)
            values 
			('${req.body.cpf}', '${req.body.nomeGestor}', '${req.body.emailGestor}',
			'${req.body.usuarioGestor}', '${req.body.senhaGestor}','${req.params.id}')`

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

router.delete('/delete/:id', function (req, res, next) {
	console.log('Excluindo um laboratório');

	let instrucaoSql = `DELETE FROM tbGestor WHERE idtbGestor = ${req.params.id}`;

	sequelize.query(instrucaoSql, {
		model: tbGestor
	}).then(resultado => {
		res.send(resultado);
	}).catch(erro => {
		console.error(erro);
		res.status(500).send(erro.message);
	});
})

router.get('/getGestores/:id', function (req, res, next) {
	console.log('Recuperando todos os Gestores');
	tbGestor.findAndCountAll({
		where: {
			fk_tbGestor_tbEmpresa: req.params.id
		}
	}).then(resultado => {
		res.json(resultado.rows);
	}).catch(erro => {
		console.error(erro);
		res.status(500).send(erro.message);
	});
});

// ---- FUNCIONARIOS ----

router.get('/getFuncionarios/:id', function (req, res, next) {
	console.log('Recuperando todos os Funcionarios');
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




	// -------- GITHUB INOVAÇÃO -------------

	router.get('/getRepositoriosGitHub/:id', function (req, res, next) {
		console.log('Recuperando todos os Funcionarios');
		tbRepositorioGitHub.findAndCountAll({
			where: {
				fk_tbRepositorioGitHub_tbGestor: req.params.id
			}
		}).then(resultado => {
			res.json(resultado.rows);
		}).catch(erro => {
			console.error(erro);
			res.status(500).send(erro.message);
		});
	});

	router.delete('/deleteRepositorio/:id', function (req, res, next) {
		console.log('Excluindo um repositorio');
	
		let instrucaoSql = `DELETE FROM tbRepositorioGitHub WHERE idtbRepositorioGitHub = ${req.params.id}`;
	
		sequelize.query(instrucaoSql, {
			model: tbRepositorioGitHub
		}).then(resultado => {
			res.send(resultado);
		}).catch(erro => {
			console.error(erro);
			res.status(500).send(erro.message);
		});
	
	})

	router.post('/cadastrarRepositorio/:id', function (req, res, next) {
		console.log('Criando um repositorio');
	
		let instrucaoSql = `INSERT INTO tbRepositorioGitHub
		(nomeRepositorioGitHub, fk_tbRepositorioGitHub_tbGestor)
				values 
				('${req.body.nomeRepositorioGitHub}','${req.params.id}');`
	
		sequelize.query(instrucaoSql, {
			model: tbRepositorioGitHub
		}).then(resultado => {
			console.log(`Registro criado: ${resultado}`)
			res.send(resultado);
		}).catch(erro => {
			console.error(erro);
			res.status(500).send(erro.message);
		});
	});




module.exports = router;