'use strict';

module.exports = (sequelize, DataTypes) => {
    let Alert = sequelize.define('Alert',{	
		idAlerta: {
			field: 'idAlerta',
			type: DataTypes.INTEGER,
			primaryKey: true,
			autoIncrement: true
		},	
        componenteInstavel: {
            field: 'componenteInstavel',
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
            type: DataTypes.STRING,
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
        },
        idMaquina: {
            field: 'idMaquina',
            type: DataTypes.INTEGER,
            allowNull: true
        }
	}, 
	{
		tableName: 'tblAlertas', 
		freezeTableName: true, 
		underscored: true,
		timestamps: false,
	});

    return Alert;
};