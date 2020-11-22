const express = require('express');
const router = express.Router();
const categorieController = require('./categorie.service');

// routes
router.post('/createCat', create);
router.get('/getall', getAll);
router.get('/current', getCurrent);
router.get('/:id', getById);
router.put('/:id', update);
router.delete('/:id', _delete);

module.exports = router;

//Create
function create(req, res, next) {
    categorieController.create(req.body)
        .then(() => res.json({}))
        .catch(err => next(err));
}



//GEtALL
function getAll(req, res, next) {
    categorieController.getAll()
        .then(Categories => res.json(Categories))
        .catch(err => next(err));
}


//GetCurrent
function getCurrent(req, res, next) {
    categorieController.getById(req.user.sub)
        .then(user => user ? res.json(user) : res.sendStatus(404))
        .catch(err => next(err));
}



//GetByID
function getById(req, res, next) {
    categorieController.getById(req.params.id)
        .then(Categories => Categories ? res.json(Categories) : res.sendStatus(404))
        .catch(err => next(err));
}


//Update
function update(req, res, next) {
    categorieController.update(req.params.id, req.body)
        .then(() => res.json({}))
        .catch(err => next(err));
}


//Delete
function _delete(req, res, next) {
    categorieController.delete(req.params.id)
        .then(() => res.json({}))
        .catch(err => next(err));
}
