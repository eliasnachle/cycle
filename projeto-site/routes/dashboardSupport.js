var express = require('express');
var router = express.Router();
var sequelize = require('../models').sequelize;
var Machine = require('../models').Machine;
var Alert = require('../models').Alert;
var Register = require('../models').Register;

router.get('/diaryUse:idContractorSession', function(req, res, next) {
	console.log('Buscando consumo diario');
	const idContractorSession = req.params.idContractorSession;
    let instrucaoSql = `SELECT AVG(cpuEmUso) AS cpuEmUso
		,AVG(espacoLivreRam) AS espacoLivreRam
	FROM tblRegistros
	WHERE idMaquina = ${idContractorSession}
		AND CONVERT(dataHoraRegistro, DATE) >= CURRENT_DATE ()
	ORDER BY dataHoraRegistro DESC;`;
	sequelize.query(instrucaoSql, {
		model: Register,
		mapToModel: true 
	})
	.then(resultado => {
		console.log(`Encontrados: ${resultado.length}`);
		res.json(resultado);
	}).catch(erro => {
		console.error(erro);
		res.status(500).send(erro.message);
	});
});

router.get('/realTimeUse:idContractorSession', function(req, res, next) {
	console.log('Buscando consumo em tempo real');
	const idContractorSession = req.params.idContractorSession;
    let instrucaoSql = `SELECT cpuEmUso,
		espacoLivreRam,
		espacoLivreDisco1,
		espacoLivreDisco2
	FROM tblRegistros
	WHERE idMaquina = ${idContractorSession}
		ORDER BY dataHoraRegistro DESC
	LIMIT 1`;
	sequelize.query(instrucaoSql, {
		model: Register,
		mapToModel: true 
	})
	.then(resultado => {
		console.log(`Encontrados: ${resultado.length}`);
		res.json(resultado);
	}).catch(erro => {
		console.error(erro);
		res.status(500).send(erro.message);
	});
});

router.get('/alerts:idContractorSession', function(req, res, next) {
	console.log('Buscando alertas');
	const idContractorSession = req.params.idContractorSession;
    let instrucaoSql = `SELECT idAlerta,
	    componenteInstavel,
	    nivelCriticidade,
	    descAlerta,
	    DATE_FORMAT(dataHoraAlerta, '%m/%d/%Y %H:%i') AS dataHoraAlerta,
		tblAlertas.idMaquina,
		tblMaquinas.apelidoMaquina
    FROM tblAlertas
	INNER JOIN tblMaquinas
		ON tblMaquinas.idMaquina = tblAlertas.idMaquina
	WHERE tblMaquinas.idUsuarioContratante = ${idContractorSession}`;
	sequelize.query(instrucaoSql, {
		model: Alert,
		mapToModel: true 
	})
	.then(resultado => {
		console.log(`Encontrados: ${resultado.length}`);
		res.json(resultado);
	}).catch(erro => {
		console.error(erro);
		res.status(500).send(erro.message);
	});
});

router.get('/detailMachine:idContractorSession', function(req, res, next) {
	console.log('Buscando configuração da maquina');
	const idContractorSession = req.params.idContractorSession;
    let instrucaoSql = `SELECT COALESCE(modeloCpu, 'Não cadastrado') AS modeloCpu
		,COALESCE(cpuFrequencia, 'Não cadastrado') AS cpuFrequencia
		,COALESCE(modeloDisco1, 'Não cadastrado') AS modeloDisco1
		,COALESCE(espacoTotalDisco1, 'Não cadastrado') AS espacoTotalDisco1
		,COALESCE(modeloDisco2, 'Não cadastrado') AS modeloDisco2
    	,COALESCE(espacoTotalDisco2, 'Não cadastrado') AS espacoTotalDisco2
		,COALESCE(espacoTotalRam, 'Não cadastrado') AS espacoTotalRam
		,COALESCE(sistemaOperacionalMaquina, 'Não cadastrado') AS sistemaOperacionalMaquina
	FROM tblMaquinas
	WHERE idMaquina = ${idContractorSession};`;
	sequelize.query(instrucaoSql, {
		model: Machine,
		mapToModel: true 
	})
	.then(resultado => {
		console.log(`Encontrados: ${resultado.length}`);
		res.json(resultado);
	}).catch(erro => {
		console.error(erro);
		res.status(500).send(erro.message);
	});
});


router.get('/:idContractorSession', function(req, res, next) {
	console.log('Buscando máquinas');
    const idContractorSession = req.params.idContractorSession;
    let instrucaoSql = `SELECT idMaquina,
    apelidoMaquina,
	tipoMaquina
    FROM tblMaquinas
	WHERE idUsuarioContratante = ${idContractorSession}`;
	sequelize.query(instrucaoSql, {
		model: Machine,
		mapToModel: true 
	})
	.then(resultado => {
		console.log(`Encontrados: ${resultado.length}`);
		res.json(resultado);
	}).catch(erro => {
		console.error(erro);
		res.status(500).send(erro.message);
	});
});



module.exports = router;