const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const schema = new Schema({
    _idObj: mongoose.Schema.Types.ObjectId,
    obj_name: { type: String, unique: true, required: true },
    obj_montant : { type: Number, unique: true, required: true },


    //IDuser
    _idUser: { type: String, unique: true,default: null }

});

module.exports = mongoose.model('Objectif', schema);
