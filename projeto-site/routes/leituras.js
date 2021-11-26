var express = require('express');
var router = express.Router();
var sequelize = require('../models').sequelize;
var Leitura = require('../models').Leitura;
var env = process.env.NODE_ENV || 'development';


// router.get('/info_user/:id_user', function (req, res, next) {
// 	console.log('Recuperando dados do Usuario logado');

// 	var id_user = req.params.id_user;

// 	let instrucaoSql = "";

// 	if (env == 'dev') {
// 		instrucaoSql = `select * from usuarios where idUsuario = ${id_user}`;
// 	} else if (env == 'production') {
// 		instrucaoSql = `select * from usuarios where idUsuario = ${id_user}`;
// 	} else {
// 		console.log("Verifique se está usando um ambiente, dev ou production")
// 	}

// 	console.log(instrucaoSql);

// 	sequelize.query(instrucaoSql, { type: sequelize.QueryTypes.SELECT })
// 		.then(resultado => {
// 			res.json(resultado[0]);
// 		}).catch(erro => {
// 			console.error(erro);
// 			res.status(500).send(erro.message);
// 		});
// });

router.get('/get-suporte-list/:idContratante', function (req, res, next) {
	console.log('Recuperando os usuarios suporte');

	let instrucaoSql = `
	SELECT
		tus.idUsuarioSuporte,
		tus.nomeSuporte,
		tus.emailSuporte,
		tus.senhaSuporte,
		tuc.nomeContratante
	FROM 
		tblUsuariosSuporte AS tus
	INNER JOIN tblUsuariosContratante as tuc
		ON tuc.idUsuarioContratante = tus.idUsuarioContratante
	WHERE 
		tus.idUsuarioContratante = ${req.params.idContratante}
	ORDER BY tus.idUsuarioSuporte DESC`;

	console.log(instrucaoSql);

	sequelize.query(instrucaoSql, { type: sequelize.QueryTypes.SELECT })
		.then(resultado => {
			res.json(resultado);
		}).catch(erro => {
			console.error(erro);
			res.status(500).send(erro.message);
		});
});

router.get('/get-machine-list/:idContratante', function (req, res, next) {
	console.log('Recuperando as máquinas do contratante');

	let instrucaoSql = `
	SELECT 
		apelidoMaquina,
		sistemaOperacionalMaquina,
		modeloCpu,
		espacoTotalRam
	FROM
		tblMaquinas
	WHERE idUsuarioContratante = ${req.params.idContratante}
	ORDER BY idMaquina`;

	console.log(instrucaoSql);

	sequelize.query(instrucaoSql, { type: sequelize.QueryTypes.SELECT })
		.then(resultado => {
			res.json(resultado);
		}).catch(erro => {
			console.error(erro);
			res.status(500).send(erro.message);
		});
});
module.exports = router;
