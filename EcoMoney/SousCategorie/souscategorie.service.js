const config = require('config.json');
const jwt = require('jsonwebtoken');
const db = require('_helpers/db');
const Souscategorie = db.SousCategorie;

module.exports = {
    getAll,
    getById,
    create,
    update,
    delete: _delete
};


async function getAll() {
    return await Souscategorie.find();
}

async function getById(id) {
    return await Souscategorie.findById(id);
}

async function create(userParam) {
    // validate
    const categorie = new Souscategorie(userParam);

    // save user
    await categorie.save();
}

async function update(id, userParam) {
    const Souscategorie = await Souscategorie.findById(id);

    // copy userParam properties to user
    Object.assign(souscategorie, userParam);

    await souscategorie.save();
}

async function _delete(id) {
    await Souscategorie.findByIdAndRemove(id);
}

