const config = require('config.json');
const jwt = require('jsonwebtoken');
const db = require('_helpers/db');
const Salaire = db.Salaire;

module.exports = {
    getAll,
    getById,
    create,
    update,
    delete: _delete
};


async function getAll() {
    return await Salaire.find();
}

async function getById(id) {
    return await Salaire.findById(id);
}

async function create(userParam) {
    // validate
    const salaire = new Salaire(userParam);

    // save user
    await salaire.save();
}

async function update(id, userParam) {
    const salaire = await Salaire.findById(id);

    // copy userParam properties to user
    Object.assign(salaire, userParam);

    await salaire.save();
}

async function _delete(id) {
    await Salaire.findByIdAndRemove(id);
}
