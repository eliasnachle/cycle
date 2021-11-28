process.env.NODE_ENV = 'dev'; // altere para 'production' ou 'dev'

var express = require('express');
const path = require('path'),
cookieParser = require('cookie-parser'),
logger = require('morgan'),
indexRouter = require('./routes/index'),
usuariosRouter = require('./routes/usuarios'),
leiturasRouter = require('./routes/leituras'),
machinesRouter = require('./routes/machines'),
app = express();

app.use(logger('dev')); 
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

app.use('/', indexRouter);
app.use('/usuarios', usuariosRouter);
app.use('/leituras', leiturasRouter);
app.use('/machines', machinesRouter)

module.exports = app;