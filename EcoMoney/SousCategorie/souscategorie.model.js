const mongoose = require('mongoose');
const Schema = mongoose.Schema;


const schema = new Schema({
    _idSouCat: mongoose.Schema.Types.ObjectId,
    SouCat_name : { type: String, unique: true, required: true },
    Etat : { type: String, unique: true, required: true },
    SouCat_montant : { type: Number, unique: true, required: true },
    periode: { type: Date, unique: true, default: Date.now },
    Reminder : { type: String, unique: true, required: true },
    //User_id,Cat_id
    _idCat: { type: String,unique: true, default: null },


});

module.exports = mongoose.model('SousCategorie', schema);
