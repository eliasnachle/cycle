	'use strict';

/* 
lista e explicação dos Datatypes:
https://codewithhugo.com/sequelize-data-types-a-practical-guide/
*/

module.exports = (sequelize, DataTypes) => {
    let UsuariosContratante = sequelize.define('UsuariosContratante',{
		idUsuarioContratante: {
			field: 'idUsuarioContratante',
			type: DataTypes.INTEGER,
			primaryKey: true,
			autoIncrement: true
		},		
		nomeContratante: {
			field: 'nomeContratante',
			type: DataTypes.STRING,
			allowNull: false
		},
		emailContratante: {
			field: 'emailContratante',
			type: DataTypes.STRING,
			allowNull: false
		},
		senhaContratante: {
			field: 'senhaContratante',
			type: DataTypes.STRING,
			allowNull: false
		},
		cpfContratante: {
			field: 'cpfContratante',
			type: DataTypes.STRING,
			allowNull: false
		},
		keyContratante: {
			field: 'keyContratante',
			type: DataTypes.STRING,
			allowNull: false
		},
		idPlano: {
			field: 'idPlano',
			type: DataTypes.INTEGER,
			allowNull: false
		}
	}, 
	{
		tableName: 'tblUsuariosContratante', 
		freezeTableName: true, 
		underscored: true,
		timestamps: false,
	});

    return UsuariosContratante;
};
