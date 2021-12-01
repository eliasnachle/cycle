var express = require('express');
var router = express.Router();
var sequelize = require('../models').sequelize;
var Machine = require('../models').Machine;
var Alert = require('../models').Alert;
var Register = require('../models').Register;
var env = process.env.NODE_ENV || 'development';

router.get('/diaryUse:idMaquina', function(req, res, next) {
	console.log('Buscando consumo diario');
	let instrucaoSql;
	const idMaquina = req.params.idMaquina;
	if(env == 'dev'){
		instrucaoSql = `SELECT AVG(cpuEmUso) AS cpuEmUso
			,AVG(espacoLivreRam) AS espacoLivreRam
		FROM tblRegistros
		WHERE idMaquina = ${idMaquina}
			AND CONVERT(dataHoraRegistro, DATE) >= CURRENT_DATE ()
		ORDER BY dataHoraRegistro DESC;`;
	} else {
		instrucaoSql = ``;
	}
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

router.get('/realTimeUse:idMaquina', function(req, res, next) {
	console.log('Buscando consumo em tempo real');
	const idMaquina = req.params.idMaquina;
    let instrucaoSql;
	if(env == 'dev'){
		instrucaoSql = `SELECT cpuEmUso,
			espacoLivreRam,
			espacoLivreDisco1,
			espacoLivreDisco2
		FROM tblRegistros
		WHERE idMaquina = ${idMaquina}
		ORDER BY dataHoraRegistro DESC
		LIMIT 1;`;		
	} else {
		instrucaoSql = ``;
	}
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

router.put('/updateAlertVisibility:idAlerta', function(req, res, next) {
	console.log('Buscando alertas');
	const idAlerta = req.params.idAlerta;
    let instrucaoSql = `UPDATE tblAlertas SET alertaVisivel = 0 WHERE idAlerta = ${idAlerta};`;
	sequelize.query(instrucaoSql, {
		model: Alert
	})
	.then(resultado => {
		console.log(`Encontrados: ${resultado.length}`);
		res.status(204).send('Card removido com sucesso!');
	}).catch(erro => {
		console.error(erro);
		res.status(500).send(erro.message);
	});
});		

router.get('/alerts:idContractorSession', function(req, res, next) {
	let instrucaoSql;
	const idContractorSession = req.params.idContractorSession;
	if(env == 'dev'){
		instrucaoSql = `SELECT idAlerta,
			componenteInstavel,
			nivelCriticidade,
			descAlerta,
			DATE_FORMAT(dataHoraAlerta, '%m/%d/%Y %H:%i') AS dataHoraAlerta,
			tblAlertas.idMaquina,
			tblMaquinas.apelidoMaquina
		FROM tblAlertas
		INNER JOIN tblMaquinas
			ON tblMaquinas.idMaquina = tblAlertas.idMaquina
		WHERE tblMaquinas.idUsuarioContratante = ${idContractorSession}
			AND tblAlertas.alertaVisivel = 1;`;
	} else {
		instrucaoSql = ``;
	}
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

router.get('/detailMachine:idMaquina', function(req, res, next) {
	let instrucaoSql;
	console.log('Buscando configuração da maquina');
	const idMaquina = req.params.idMaquina;
	instrucaoSql = `SELECT COALESCE(modeloCpu, 'Não cadastrado') AS modeloCpu
		,COALESCE(cpuFrequencia, 'Não cadastrado') AS cpuFrequencia
		,COALESCE(modeloDisco1, 'Não cadastrado') AS modeloDisco1
		,COALESCE(espacoTotalDisco1, 'Não cadastrado') AS espacoTotalDisco1
		,COALESCE(modeloDisco2, 'Não cadastrado') AS modeloDisco2
    	,COALESCE(espacoTotalDisco2, 'Não cadastrado') AS espacoTotalDisco2
		,COALESCE(espacoTotalRam, 'Não cadastrado') AS espacoTotalRam
		,COALESCE(sistemaOperacionalMaquina, 'Não cadastrado') AS sistemaOperacionalMaquina
	FROM tblMaquinas
	WHERE idMaquina = ${idMaquina};`;
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
	WHERE idUsuarioContratante = ${idContractorSession}
	ORDER BY tipoMaquina DESC;`;
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