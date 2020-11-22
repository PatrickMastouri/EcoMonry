const config = require('config.json');
const jwt = require('jsonwebtoken');
const db = require('_helpers/db');
const Objectif = db.Objectif;

module.exports = {
    getAll,
    getById,
    create,
    update,
    delete: _delete
};


async function getAll() {
    return await Objectif.find();
}

async function getById(id) {
    return await Objectif.findById(id);
}

async function create(userParam) {
    // validate
    const objectif = new Objectif(userParam);

    // save user
    await objectif.save();
}

async function update(id, userParam) {
    const objectif = await Objectif.findById(id);

    // copy userParam properties to user
    Object.assign(Objectif, userParam);

    await Objectif.save();
}

async function _delete(id) {
    await Objectif.findByIdAndRemove(id);
}
