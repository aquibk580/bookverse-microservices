const express = require("express");
const router = express.Router();
const client = require("../client/grpc-user-client.js");

// GET /
router.get("/", (req, res) => {
  client.GetUser({}, (err, response) => {
    if (err) return res.status(500).json({ error: err.message });
    res.json(response.users);
  });
});

// POST /
router.post("/", (req, res) => {
  const { name, email, password } = req.body;
  client.CreateUser({ name, email, password }, (err, _) => {
    if (err) return res.status(500).json({ error: err.message });
    res.json({ message: "User created successfully" });
  });
});

// DELETE /
router.delete("/:id", (req, res) => {
  const id = req.params.id;
  client.DeleteUser({ id }, (err, _) => {
    if (err) return res.status(500).json({ error: err.message });
    res.json({ message: "User deleted successfully" });
  });
});

module.exports = router;
