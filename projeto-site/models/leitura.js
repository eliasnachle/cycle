'use strict';

/* 
lista e explicação dos Datatypes:
https://codewithhugo.com/sequelize-data-types-a-practical-guide/
*/

module.exports = (sequelize, DataTypes) => {
    let Leitura = sequelize.define('tblRegistros',{	
		id: {
			field: 'idRegistro',
			type: DataTypes.INTEGER,
			primaryKey: true,
			autoIncrement: true
		},	
		cpuUso: {
			field: 'cpuEmUso',
			type: DataTypes.REAL,
			allowNull: false
		},
		espacoDisco1: {
			field: 'espacoLivreDisco1',
			type: DataTypes.REAL,
			allowNull: false
		},
		espacoRAM: {
			field: 'espacoLivreRam',
			type: DataTypes.REAL, 
			allowNull: false
		},
		dataRegistro: {
			field: 'dataHoraRegistro',
			type: DataTypes.REAL, 
			allowNull: true
		},
		idMaquina: {
			field: 'idMaquina',
			type: DataTypes.INTEGER, 
			allowNull: true
		},
		espacoDisco2: {
			field: 'espacoLivreDisco2',
			type: DataTypes.INTEGER, 
			allowNull: true
		}
	}, 
	{
		tableName: 'tblRegistros', 
		freezeTableName: true, 
		underscored: true,
		timestamps: false,
	});

    return Leitura;
};
