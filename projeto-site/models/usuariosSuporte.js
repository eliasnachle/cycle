	'use strict';

/* 
lista e explicação dos Datatypes:
https://codewithhugo.com/sequelize-data-types-a-practical-guide/
*/

module.exports = (sequelize, DataTypes) => {
    let UsuariosSuporte = sequelize.define('UsuariosSuporte',{
		idUsuarioSuporte: {
			field: 'idUsuarioSuporte',
			type: DataTypes.INTEGER,
			primaryKey: true,
			autoIncrement: true
		},		
		nomeSuporte: {
			field: 'nomeSuporte',
			type: DataTypes.STRING,
			allowNull: false
		},
		emailSuporte: {
			field: 'emailSuporte',
			type: DataTypes.STRING,
			allowNull: false
		},
		senhaSuporte: {
			field: 'senhaSuporte',
			type: DataTypes.STRING,
			allowNull: false
		},
		idUsuarioContratante: {
			field: 'idUsuarioContratante',
			type: DataTypes.INTEGER,
			allowNull: false
		}
	}, 
	{
		tableName: 'tblUsuariosSuporte', 
		freezeTableName: true, 
		underscored: true,
		timestamps: false,
	});

    return UsuariosSuporte;
};
