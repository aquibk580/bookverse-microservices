const express = require("express");
const router = express.Router();
const client = require("../client/grpc-book-client.js");

// GET /books
router.get("/", (req, res) => {
  client.GetBooks({}, (err, response) => {
    if (err) return res.status(500).json({ error: err.message });
    res.json(response.books);
  });
});

// POST /books
router.post("/", (req, res) => {
  const { title, author, price } = req.body;
  client.AddBook({ title, author, price }, (err, _) => {
    if (err) return res.status(500).json({ error: err.message });
    res.json({ message: "Book added successfully" });
  });
});

// DELETE /books/:id
router.delete("/:id", (req, res) => {
  const id = parseInt(req.params.id);
  client.DeleteBook({ id }, (err, _) => {
    if (err) return res.status(500).json({ error: err.message });
    res.json({ message: "Book deleted successfully" });
  });
});

module.exports = router;
