const config = require('config.json');
const mongoose = require('mongoose');
const connectionOptions = { useCreateIndex: true, useNewUrlParser: true, useUnifiedTopology: true, useFindAndModify: false };
mongoose.connect(process.env.MONGODB_URI || config.connectionString, connectionOptions);
mongoose.Promise = global.Promise;

module.exports = {
    User: require('../users/user.model'),
    Categorie: require('../Categorie/categorie.model'),
    Objectif: require('../Objectif/objectif.model'),
    SousCategorie: require('../SousCategorie/souscategorie.model'),
    Salaire: require('../Salaire/salaire.model')
};
