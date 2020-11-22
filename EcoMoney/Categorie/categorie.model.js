const mongoose = require('mongoose');
const Schema = mongoose.Schema;



const schema = new Schema({
    _idCat: mongoose.Schema.Types.ObjectId,
    Name_categorie : { type: String, unique: true, required: true },
    description : { type: String, unique: true, required: true },
    CatImage : { type: String, unique: true, required: true },
    Total : { type: Number, unique: true, required: true },
    //User_id
    _idUser: { type: String, unique: true, default: null }
});




module.exports = mongoose.model('Categorie', schema);
