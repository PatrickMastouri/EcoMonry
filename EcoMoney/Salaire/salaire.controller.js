const express = require('express');
const router = express.Router();
const salaireController = require('./salaire.service');

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
    salaireController.create(req.body)
        .then(() => res.json({}))
        .catch(err => next(err));
}



//GEtALL
function getAll(req, res, next) {
    salaireController.getAll()
        .then(salaire => res.json(salaire))
        .catch(err => next(err));
}


//GetCurrent
function getCurrent(req, res, next) {
    salaireController.getById(req.salaire.sub)
        .then(salaire => salaire ? res.json(salaire) : res.sendStatus(404))
        .catch(err => next(err));
}



//GetByID
function getById(req, res, next) {
    salaireController.getById(req.params.id)
        .then(salaire => salaire ? res.json(salaire) : res.sendStatus(404))
        .catch(err => next(err));
}


//Update
function update(req, res, next) {
    salaireController.update(req.params.id, req.body)
        .then(() => res.json({}))
        .catch(err => next(err));
}


//Delete
function _delete(req, res, next) {
    salaireController.delete(req.params.id)
        .then(() => res.json({}))
        .catch(err => next(err));
}
