var express = require('express');
var router = express.Router();
var sequelize = require('../models').sequelize;
var tbComputador = require('../models').tbComputador;
var tbProcessos = require('../models').tbProcessos;

/* Recuperar as últimas N leituras */
router.get('/ultimas/:id/:id2', function (req, res, next) {

	// quantas são as últimas leituras que quer? 8 está bom?
	const limite_linhas = 7;

	var idtbComputador = req.params.id;
	var idComputador   = req.params.id2;

	console.log(`Recuperando as ultimas ${limite_linhas} leituras`);
	console.log(idComputador);

	const instrucaoSql = `select top ${limite_linhas} idtbRegistroDesempenhoMaquina, desempenhoCpu, desempenhoRam,
	FORMAT(dataHoraDesempenho,'HH:mm:ss') as momento_grafico
	from tbComputador
	inner join tbRegistroDesempenhoMaquina on fk_tbRegistroDesempenhoMaquina_tbComputador = ${idtbComputador}
	where idComputador = '${idComputador}'
	order by idtbRegistroDesempenhoMaquina desc;`;

	sequelize.query(instrucaoSql, {
		model: tbComputador,
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

router.get('/processos/:id', function (req, res, next) {

	// quantas são as últimas leituras que quer? 8 está bom?
	

	var idtbComputador = req.params.id;

	console.log(`Recuperando os processos`);

	const instrucaoSql = `
	select top 20 pidProcesso, nomeProcesso, cpuProcesso, ramProcesso, 	FORMAT(dataHoraProcesso
		, 'dd-MM-yyyy HH:mm:ss') as dataHoraProcesso
		from  tbComputador
		inner join tbProcessos on fk_tbProcessos_tbComputador = ${idtbComputador}
		where 
		FORMAT(dataHoraProcesso
		, 'dd-MM-yyyy') = 
		FORMAT(GETDATE() AT TIME ZONE 'UTC' AT TIME ZONE 
		'Greenwich Standard Time', 'dd-MM-yyyy') 
		order by idtbProcesso  desc;
`;

	sequelize.query(instrucaoSql, {
		model: tbProcessos,
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


// tempo real (último valor de cada leitura)
/*router.get('/tempo-real', function (req, res, next) {
	
	console.log(`Recuperando a ultima leitura`);

	const instrucaoSql = `select top 4 temperatura, umidade, FORMAT(momento,'HH:mm:ss') as momento_grafico, idcaminhao from leitura order by id desc`;

	sequelize.query(instrucaoSql, { type: sequelize.QueryTypes.SELECT })
		.then(resultado => {
			res.json(resultado[0]);
		}).catch(erro => {
			console.error(erro);
			res.status(500).send(erro.message);
		});
  
});
*/



router.get('/tempo-real2/:id/:ids', function (req, res, next) {
	console.log('Recuperando leituras');

	//var idcaminhao = req.body.idcaminhao; // depois de .body, use o nome (name) do campo em seu formulário de login
	var idtbComputador = req.params.id;
	var idComputador   = req.params.ids;
	console.log(idComputador);

	let instrucaoSql = ` select top 1 idtbRegistroDesempenhoMaquina, desempenhoCpu, desempenhoRam,
	FORMAT(dataHoraDesempenho,'HH:mm:ss') as momento_grafico
	from tbComputador
	inner join tbRegistroDesempenhoMaquina on fk_tbRegistroDesempenhoMaquina_tbComputador = ${idtbComputador}
	where idComputador = '${idComputador}'
	order by idtbRegistroDesempenhoMaquina desc;`;

	console.log(instrucaoSql);

	sequelize.query(instrucaoSql, { type: sequelize.QueryTypes.SELECT })
		.then(resultado => {
			res.json(resultado[0]);
		}).catch(erro => {
			console.error(erro);
			res.status(500).send(erro.message);
		});
});

// estatísticas (max, min, média, mediana, quartis etc)
router.get('/estatisticas', function (req, res, next) {

	console.log(`Recuperando as estatísticas atuais`);

	const instrucaoSql = `select 
							max(temperatura) as temp_maxima, 
							min(temperatura) as temp_minima, 
							avg(temperatura) as temp_media,
							max(umidade) as umidade_maxima, 
							min(umidade) as umidade_minima, 
							avg(umidade) as umidade_media 
						from leitura`;

	sequelize.query(instrucaoSql, { type: sequelize.QueryTypes.SELECT })
		.then(resultado => {
			res.json(resultado[0]);
		}).catch(erro => {
			console.error(erro);
			res.status(500).send(erro.message);
		});

});

router.get('/getComputador/:id', function (req, res, next) {
	console.log('Get no computador');
	tbComputador.findAndCountAll({
		where: {
			fk_tbComputador_tbFuncionario: req.params.id
		}
	}).then(resultado => {
		res.json(resultado.rows);
	}).catch(erro => {
		console.error(erro);
		res.status(500).send(erro.message);
	});
});


module.exports = router;
