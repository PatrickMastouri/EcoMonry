const express = require('express');
const router = express.Router();
const objectifController = require('./objectif.service');

// routes
router.post('/createObj', create);
router.get('/getall', getAll);
router.get('/current', getCurrent);
router.get('/:id', getById);
router.put('/:id', update);
router.delete('/:id', _delete);

module.exports = router;

//Create
function create(req, res, next) {
    objectifController.create(req.body)
        .then(() => res.json({}))
        .catch(err => next(err));
}



//GEtALL
function getAll(req, res, next) {
    objectifController.getAll()
        .then(objectif => res.json(objectif))
        .catch(err => next(err));
}


//GetCurrent
function getCurrent(req, res, next) {
    objectifController.getById(req.user.sub)
        .then(objectif => objectif ? res.json(objectif) : res.sendStatus(404))
        .catch(err => next(err));
}



//GetByID
function getById(req, res, next) {
    objectifController.getById(req.params.id)
        .then(objectif => objectif ? res.json(objectif) : res.sendStatus(404))
        .catch(err => next(err));
}


//Update
function update(req, res, next) {
    objectifController.update(req.params.id, req.body)
        .then(() => res.json({}))
        .catch(err => next(err));
}


//Delete
function _delete(req, res, next) {
    objectifController.delete(req.params.id)
        .then(() => res.json({}))
        .catch(err => next(err));
}
