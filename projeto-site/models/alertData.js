'use strict';

/* 
lista e explicação dos Datatypes:
https://codewithhugo.com/sequelize-data-types-a-practical-guide/
*/

module.exports = (sequelize, DataTypes) => {
    let AlertData = sequelize.define('AlertData',{
		idAlerta: {
			field: 'idAlerta',
			type: DataTypes.INTEGER,
			primaryKey: true,
			autoIncrement: true
		},		
		componenteEstavel: {
			field: 'componenteEstavel',
			type: DataTypes.STRING,
			allowNull: true
		},
		nivelCriticidade: {
			field: 'nivelCriticidade',
			type: DataTypes.STRING,
			allowNull: true
		},
		descAlerta: {
			field: 'descAlerta',
			type: DataTypes.DECIMAL,
			allowNull: true
		},
		dataHoraAlerta: {
			field: 'dataHoraAlerta',
			type: DataTypes.DATE,
			allowNull: true
		},
		idRegistro: {
			field: 'idRegistro',
			type: DataTypes.INTEGER,
			allowNull: true
		}
	}, 
	{
		tableName: 'tblAlertas', 
		freezeTableName: true, 
		underscored: true,
		timestamps: true,
	});

    return AlertData;
}