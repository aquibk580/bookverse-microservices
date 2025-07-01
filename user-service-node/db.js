const mongoose = require("mongoose");

const connectToMongo = async () => {
  try {
    await mongoose.connect(process.env.DATABASE_URL);
    console.log("Connected to mongoDB Successfully");
  } catch (error) {
    console.log(error);
  }
};

module.exports = connectToMongo;
