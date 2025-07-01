const express = require("express");
const PORT = 3000;
const bookRoutes = require("./routes/bookRoutes.js");
const userRoutes = require("./routes/userRoutes.js");

const app = express();
app.use(express.json());

app.use("/books", bookRoutes);
app.use("/users", userRoutes);

app.listen(PORT, () => {
  console.log(`🚀 Express server listening on ${PORT}`);
});
