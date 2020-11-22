const express = require('express');
const router = express.Router();
const SousCatController = require('./souscategorie.service');

// routes
router.post('/createSouCat', create);
router.get('/getall', getAll);
router.get('/current', getCurrent);
router.get('/:id', getById);
router.put('/:id', update);
router.delete('/:id', _delete);

module.exports = router;

//Create
function create(req, res, next) {
    SousCatController.create(req.body)
        .then(() => res.json({}))
        .catch(err => next(err));
}



//GEtALL
function getAll(req, res, next) {
    SousCatController.getAll()
        .then(SousCategorie => res.json(SousCategorie))
        .catch(err => next(err));
}


//GetCurrent
function getCurrent(req, res, next) {
    SousCatController.getById(req.user.sub)
        .then(SousCategorie => SousCategorie ? res.json(SousCategorie) : res.sendStatus(404))
        .catch(err => next(err));
}



//GetByID
function getById(req, res, next) {
    SousCatController.getById(req.params.id)
        .then(SousCategorie => SousCategorie ? res.json(SousCategorie) : res.sendStatus(404))
        .catch(err => next(err));
}


//Update
function update(req, res, next) {
    SousCatController.update(req.params.id, req.body)
        .then(() => res.json({}))
        .catch(err => next(err));
}


//Delete
function _delete(req, res, next) {
    SousCatController.delete(req.params.id)
        .then(() => res.json({}))
        .catch(err => next(err));
}
