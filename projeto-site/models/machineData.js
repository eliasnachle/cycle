'use strict';

/* 
lista e explicação dos Datatypes:
https://codewithhugo.com/sequelize-data-types-a-practical-guide/
*/

module.exports = (sequelize, DataTypes) => {
    let MachineData = sequelize.define('MachineData',{
		idRegistro: {
			field: 'idRegistro',
			type: DataTypes.INTEGER,
			primaryKey: true,
			autoIncrement: true
		},		
		cpuEmUso: {
			field: 'cpuEmUso',
			type: DataTypes.DECIMAL,
			allowNull: true
		},
		temperaturaCpu: {
			field: 'temperaturaCpu',
			type: DataTypes.DECIMAL,
			allowNull: true
		},
		espacoLivreDisco1: {
			field: 'sistemaOperacionalMaquina',
			type: DataTypes.DECIMAL,
			allowNull: true
		},
		espacoLivreRam: {
			field: 'espacoLivreRam',
			type: DataTypes.DECIMAL,
			allowNull: true
		},
		dataHoraRegistro: {
			field: 'dataHoraRegistro',
			type: DataTypes.DATE,
			allowNull: true
		},
		espacoLivreDisco2: {
			field: 'espacoLivreDisco2',
			type: DataTypes.DECIMAL,
			allowNull: true
		}
	}, 
	{
		tableName: 'tblRegistros', 
		freezeTableName: true, 
		underscored: true,
		timestamps: true,
	});

    return MachineData;
}