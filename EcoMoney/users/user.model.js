const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const schema = new Schema({
    _idUser: mongoose.Schema.Types.ObjectId,
    first_Name: { type: String, unique: true, required: true },
    last_Name: { type: String, unique: true, required: true },
    Date: { type: Date, unique: true, default: Date.now },
    image: { type: String, unique: true, required: true },
    Adress: { type: String, unique: true, required: true },
    Email: { type: String, unique: true, required: true },
    phone: { type: Number, require: true, unique: true },
    //MDP
    hash: { type: String, unique: true, required: true },
    createdDate: { type: Date, unique: true, default: Date.now }
});

schema.set('toJSON', {
    virtuals: true,
    versionKey: false,
    transform: function (doc, ret) {
        delete ret._idUser;
        delete ret.hash;
    }
});

module.exports = mongoose.model('User', schema);
