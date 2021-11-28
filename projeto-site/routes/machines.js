var express = require('express');
var router = express.Router();
var sequelize = require('../models').sequelize;
var Machine = require('../models').Machine;

router.get('/:idContractorSession', function(req, res, next) {
	console.log('Buscando máquinas');
    const idContractorSession = req.params.idContractorSession;
    let instrucaoSql = `SELECT 
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