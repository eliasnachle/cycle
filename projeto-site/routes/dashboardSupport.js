var express = require('express');
var router = express.Router();
var sequelize = require('../models').sequelize;
var Machine = require('../models').Machine;
var Alert = require('../models').Alert;
var Register = require('../models').Register;
var UsuariosSuporte = require('../models').UsuariosSuporte;
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
		instrucaoSql = `SELECT TOP 1 cpuEmUso,
			espacoLivreRam
		FROM tblRegistros
		WHERE idMaquina = ${idMaquina}
 		ORDER BY dataHoraRegistro DESC`;
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
		instrucaoSql = `SELECT TOP 1 cpuEmUso,
			espacoLivreRam,
			espacoLivreDisco1,
			espacoLivreDisco2
		FROM tblRegistros
		WHERE idMaquina = ${idMaquina}
 		ORDER BY dataHoraRegistro DESC`;
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
	});
});

router.get('/realChartTimeUse:idMaquina:component', function(req, res, next) {
	const idMaquina = req.params.idMaquina,
	component = req.params.component;
	console.log('Buscando consumo de CPU em tempo real');
    let instrucaoSql;
	if(env == 'dev'){
		switch(component){
			case 'cpu':
				instrucaoSql = `SELECT cpuEmUso AS componenteEmUso
				FROM tblRegistros
				WHERE idMaquina = ${idMaquina}
				ORDER BY dataHoraRegistro DESC
				LIMIT 3;`;
				break;
			case 'memory':
				instrucaoSql = `SELECT (espacoTotalRam - espacoLivreRam) AS componenteEmUso
				FROM tblRegistros
				INNER JOIN tblMaquinas
					ON tblRegistros.idMaquina = tblMaquinas.idMaquina
				WHERE tblRegistros.idMaquina = ${idMaquina}
				ORDER BY dataHoraRegistro DESC
				LIMIT 3;`;
				break;
			case 'disk':
				instrucaoSql = `SELECT (espacoTotalDisco1 - espacoLivreDisco1) AS componenteEmUso  
				FROM tblRegistros
				INNER JOIN tblMaquinas
					ON tblRegistros.idMaquina = tblMaquinas.idMaquina
				WHERE tblRegistros.idMaquina = ${idMaquina}
				ORDER BY dataHoraRegistro DESC
				LIMIT 3;`;
				break;					
		}
	} else {
		switch(component){
			case 'cpu':
				instrucaoSql = `SELECT TOP 3 cpuEmUso AS componenteEmUso
				FROM tblRegistros
				WHERE idMaquina = ${idMaquina}
				ORDER BY dataHoraRegistro DESC`;
				break;
			case 'memory':
				instrucaoSql = `SELECT TOP 3 (espacoTotalRam - espacoLivreRam) AS componenteEmUso
				FROM tblRegistros
				INNER JOIN tblMaquinas
					ON tblRegistros.idMaquina = tblMaquinas.idMaquina
				WHERE tblRegistros.idMaquina = ${idMaquina}
				ORDER BY dataHoraRegistro DESC`;
				break;
			case 'disk':
				instrucaoSql = `SELECT TOP 3 (espacoTotalDisco1 - espacoLivreDisco1) AS componenteEmUso  
				FROM tblRegistros
				INNER JOIN tblMaquinas
					ON tblRegistros.idMaquina = tblMaquinas.idMaquina
				WHERE tblRegistros.idMaquina = ${idMaquina}
				ORDER BY dataHoraRegistro DESC`;
				break;					
		}		
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
	});
});

router.put('/updateUsernameSupport/:idSupportUser/:valueIpt', function(req, res, next) {
	console.log('Alterando username do usuario');
	const idSupportUser = req.params.idSupportUser,
	valueIpt = req.params.valueIpt;
    let instrucaoSql = `UPDATE tblUsuariosSuporte SET nomeSuporte = '${valueIpt}' WHERE idUsuarioSuporte = ${idSupportUser};`;
	sequelize.query(instrucaoSql, {
		model: UsuariosSuporte
	})
	.then(resultado => {
		console.log(`Encontrados: ${resultado.length}`);
		res.status(204).send('Usuario alterado com sucesso!');
	}).catch(erro => {
		console.error(erro);
	});
});	


router.put('/updatePasswordSupport/:idSupportUser/:valueIpt', function(req, res, next) {
	console.log('Alterando senha do usuario');
	const idSupportUser = req.params.idSupportUser,
	valueIpt = req.params.valueIpt;
    let instrucaoSql = `UPDATE tblUsuariosSuporte SET senhaSuporte = '${valueIpt}' WHERE idUsuarioSuporte = ${idSupportUser};`;
	sequelize.query(instrucaoSql, {
		model: UsuariosSuporte
	})
	.then(resultado => {
		console.log(`Encontrados: ${resultado.length}`);
		res.status(204).send('Senha alterado com sucesso!');
	}).catch(erro => {
		console.error(erro);
	});
});	

router.put('/updateAlertVisibility:idAlerta', function(req, res, next) {
	console.log('Alterando visibilidade do alerta');
	const idAlerta = req.params.idAlerta;
    let instrucaoSql = `UPDATE tblAlertas SET alertaVisivel = 0 WHERE idAlerta = ${idAlerta};`;
	sequelize.query(instrucaoSql, {
		model: Alert
	})
	.then(resultado => {
		console.log(`Encontrados: ${resultado.length}`);
		res.status(204).send('Visibilidade de alerta alterada!');
	}).catch(erro => {
		console.error(erro);
	});
});	

router.get('/criticalRegisters', function(req, res, next) {
	let instrucaoSql;
	if(env == 'dev'){
		instrucaoSql = `SELECT componenteInstavel,
			nivelCriticidade,
			descAlerta,
			DATE_FORMAT(dataHoraAlerta, '%m/%d/%Y %H:%i') AS dataHoraAlerta,
			apelidoMaquina
		FROM tblRegistros tblR
		INNER JOIN tblMaquinas tblM 
			ON tblM.idMaquina = tblR.idMaquina
		INNER JOIN tblAlertas tblA 
			ON tblR.idMaquina = tblA.idMaquina
			AND tblM.idMaquina = tblA.idMaquina
		WHERE nivelCriticidade > 2;`;
	} else {
		instrucaoSql = `SELECT componenteInstavel,
	    	nivelCriticidade,
	    	descAlerta,
	    	convert(varchar(18), GETDATE() ,13),
		tbm.apelidoMaquina
    	FROM tblAlertas tba
		INNER JOIN tblMaquinas tbm on
	 		tbm.idMaquina = tba.idMaquina
		WHERE nivelCriticidade = 'Crítico'`;
	}
	sequelize.query(instrucaoSql, {
		model:  Alert,
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

router.get('/userDetails:idSupportUser', function(req, res, next) {
	const idSupportUser = req.params.idSupportUser;
	let instrucaoSql = `SELECT nomeSuporte,
		emailSuporte,
		senhaSuporte 
	FROM tblusuariossuporte
	WHERE idUsuarioSuporte = ${idSupportUser};`;
	sequelize.query(instrucaoSql, {
		model: UsuariosSuporte,
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
		instrucaoSql = `SELECT idAlerta,
	    componenteInstavel,
	    nivelCriticidade,
	    descAlerta,
	    convert(varchar(18), GETDATE() ,13),
		tbm.idMaquina,
		tbm.apelidoMaquina
    FROM tblAlertas tba
	INNER JOIN tblMaquinas tbm on
	 tbm.idMaquina = tba.idMaquina
	WHERE tbm.idUsuarioContratante in (
		select tuc.idUsuarioContratante from tblUsuariosSuporte tus inner join 
		tblUsuariosContratante tuc on tus.idUsuarioContratante = tuc.idUsuarioContratante
		where idUsuarioSuporte = ${idContractorSession}
		)`;
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
    from tblMaquinas tbm 
	inner join tblUsuariosContratante tus on 
	tus.idUsuarioContratante = tbm.idUsuarioContratante 
		where tus.idUsuarioContratante in (
			select tuc.idUsuarioContratante from tblUsuariosSuporte tus 
			inner join tblUsuariosContratante tuc on tus.idUsuarioContratante = tuc.idUsuarioContratante
		where idUsuarioSuporte = 56
	)`;
	sequelize.query(instrucaoSql, { type: sequelize.QueryTypes.SELECT })
	.then(resultado => {
		console.log(`Encontrados: ${resultado.length}`);
		res.json(resultado);
	}).catch(erro => {
		console.error(erro);
		res.status(500).send(erro.message);
	});
});


module.exports = router;