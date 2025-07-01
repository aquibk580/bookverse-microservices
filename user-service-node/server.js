const express = require("express");
const dotenv = require("dotenv");
const connectToMongo = require("./db.js");
const userRoutes = require("./routes/userRoutes.js");
const PORT = 5000;

dotenv.config();
connectToMongo();

const app = express();
app.use("/users", userRoutes);

app.listen(PORT, () => {
  console.log(`User service is running on ${PORT}`);
});
