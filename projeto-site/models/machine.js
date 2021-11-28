'use strict';

module.exports = (sequelize, DataTypes) => {
    let Machine = sequelize.define('Machine',{	
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
            allowNull: true
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
            allowNull: true
        },
        cpuFrequencia: {
            field: 'cpuFrequencia',
            type: DataTypes.DOUBLE,
            allowNull: true
        },
        modeloDisco1: {
            field: 'modeloDisco1',
            type: DataTypes.STRING,
            allowNull: true
        },
        modeloDisco1: {
            field: 'modeloDisco1',
            type: DataTypes.STRING,
            allowNull: true
        },
        espacoTotalDisco1: {
            field: 'espacoTotalDisco1',
            type: DataTypes.DOUBLE,
            allowNull: true
        },
        espacoTotalDisco2: {
            field: 'espacoTotalDisco2',
            type: DataTypes.DOUBLE,
            allowNull: true
        },
        espacoTotalRam: {
            field: 'espacoTotalRam',
            type: DataTypes.DOUBLE,
            allowNull: true
        },
        idUsuarioContratante: {
            field: 'idUsuarioContratante',
            type: DataTypes.INTEGER,
            allowNull: false
        }
	}, 
	{
		tableName: 'tblMaquinas', 
		freezeTableName: true, 
		underscored: true,
		timestamps: false,
	});

    return Machine;
};