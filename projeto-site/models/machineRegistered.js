'use strict';

/* 
lista e explicação dos Datatypes:
https://codewithhugo.com/sequelize-data-types-a-practical-guide/
*/

module.exports = (sequelize, DataTypes) => {
    let MachineContratant = sequelize.define('MachineContratant',{
		idMaquina: {
			field: 'idMaquina',
			type: DataTypes.INTEGER,
			primaryKey: true,
			autoIncrement: true
		},		
		apelidoMaquina: {
			field: 'apelidoMaquina',
			type: DataTypes.STRING,
			allowNull: true
		},
		tipoMaquina: {
			field: 'tipoMaquina',
			type: DataTypes.STRING,
			allowNull: false
		},
		sistemaOperacionalMaquina: {
			field: 'sistemaOperacionalMaquina',
			type: DataTypes.STRING,
			allowNull: true
		},
		idProcessador: {
			field: 'idProcessador',
			type: DataTypes.STRING,
			allowNull: true
		},
		modeloCpu: {
			field: 'modeloCpu',
			type: DataTypes.STRING,
			allowNull: false
		},
		cpuFrequencia: {
			field: 'cpuFrequencia',
			type: DataTypes.DECIMAL,
			allowNull: true
		},
		modeloDisco1: {
			field: 'modeloDisco1',
			type: DataTypes.STRING,
			allowNull: false
		},
		espacoTotalDisco1: {
			field: 'espacoTotalDisco1',
			type: DataTypes.DECIMAL,
			allowNull: true
		},
		espacoTotalRam: {
			field: 'espacoTotalRam',
			type: DataTypes.DECIMAL,
			allowNull: true
		},
		idUsuarioContratante: {
			field: 'idUsuarioContratante',
			type: DataTypes.INTEGER,
			allowNull: true
		},
		modeloDisco2: {
			field: 'modeloDisco2',
			type: DataTypes.STRING,
			allowNull: true
		},
		espacoTotalDisco2: {
			field: 'espacoTotalDisco2',
			type: DataTypes.DECIMAL,
			allowNull: true
		}
	}, 
	{
		tableName: 'tblMaquinas', 
		freezeTableName: true, 
		underscored: true,
		timestamps: false,
	});

    return MachineContratant;
};