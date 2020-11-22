const config = require('config.json');
const jwt = require('jsonwebtoken');
const db = require('_helpers/db');
const Categorie = db.Categorie;

module.exports = {
    getAll,
    getById,
    create,
    update,
    delete: _delete
};


async function getAll() {
    return await Categorie.find();
}

async function getById(id) {
    return await Categorie.findById(id);
}

async function create(userParam) {
    // validate
    const categorie = new Categorie(userParam);

    // save user
    await categorie.save();
}

async function update(id, userParam) {
    const categorie = await Categorie.findById(id);

    // copy userParam properties to user
    Object.assign(categorie, userParam);

    await categorie.save();
}

async function _delete(id) {
    await Categorie.findByIdAndRemove(id);
}
