'use strict';

module.exports = (sequelize, DataTypes) => {
    let Register = sequelize.define('Register',{	
		idMaqidRegistrouina: {
			field: 'idRegistro',
			type: DataTypes.INTEGER,
			primaryKey: true,
			autoIncrement: true
		},	
        cpuEmUso: {
            field: 'cpuEmUso',
            type: DataTypes.DOUBLE,
            allowNull: true
        },
        temperaturaCpu: {
            field: 'temperaturaCpu',
            type: DataTypes.DOUBLE,
            allowNull: true
        },
        espacoLivreDisco1: {
            field: 'espacoLivreDisco1',
            type: DataTypes.STRING,
            allowNull: true
        },
        espacoLivreDisco2: {
            field: 'espacoLivreDisco2',
            type: DataTypes.STRING,
            allowNull: true
        },
        espacoLivreRam: {
            field: 'espacoLivreRam',
            type: DataTypes.DOUBLE,
            allowNull: true
        },
        dataHoraRegistro: {
            field: 'dataHoraRegistro',
            type: DataTypes.DATE,
            allowNull: true
        },
        idMaquina: {
            field: 'idMaquina',
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

    return Register;
};