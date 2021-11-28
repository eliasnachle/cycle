var express = require('express');
var router = express.Router();
var sequelize = require('../models').sequelize;
var Machine = require('../models').Machine;
var Alert = require('../models').Alert;

router.get('/alerts:idContractorSession', function(req, res, next) {
	console.log('Buscando alertas');
	const idContractorSession = req.params.idContractorSession;
    let instrucaoSql = `SELECT idAlerta,
	    componenteInstavel,
	    nivelCriticidade,
	    descAlerta,
	    DATE_FORMAT(dataHoraAlerta, '%m/%d/%Y %H:%i') as dataHoraAlerta,
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