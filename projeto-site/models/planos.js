'use strict';

/* 
lista e explicação dos Datatypes:
https://codewithhugo.com/sequelize-data-types-a-practical-guide/
*/

module.exports = (sequelize, DataTypes) => {
    let Planos = sequelize.define('Planos',{
		idPlano: {
			field: 'idPlano',
			type: DataTypes.INTEGER,
			primaryKey: true,
			autoIncrement: true
		},		
		nomePlano: {
			field: 'nomePlano',
			type: DataTypes.STRING,
			allowNull: false
		},
		qntMaquinas: {
			field: 'qntMaquinas',
			type: DataTypes.STRING,
			allowNull: false
		},
		valorPlano: {
			field: 'valorPlano',
			type: DataTypes.STRING,
			allowNull: false
		}
	}, 
	{
		tableName: 'tblPlanos', 
		freezeTableName: true, 
		underscored: true,
		timestamps: false,
	});

    return Planos;
};
