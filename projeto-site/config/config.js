module.exports = {
  production: {
    username: 'grupo4_2adsb', //nome usuario grupoPiCRUD
    password: 'group4_cycle', //senha
    database: 'database_cycle',  // grupo4
    host: 'cycle-project.database.windows.net',  // localhost:3000/
    dialect: 'mssql',
    xuse_env_variable: 'DATABASE_URL',
    dialectOptions: {
      options: {
        encrypt: true
      }
    },
    pool: { 
      max: 5,
      min: 1,
      acquire: 5000,
      idle: 30000,
      connectTimeout: 5000
    }
  }
};
 
