const mongoose = require('mongoose');
const Schema = mongoose.Schema;


const schema = new Schema({
    _idSal: mongoose.Schema.Types.ObjectId,
    Salaire: { type: Number, unique: true, required: true },

    payment_way: {
        type: String,
        unique: true,
        enum : ['Daily', 'Weekly','Monthly','Annualy'],
        default: 'Monthly'
    },
    //User_id,Cat_id
    _idUser: { type: String, unique: true,default: null }


});

module.exports = mongoose.model('Salaire', schema);
