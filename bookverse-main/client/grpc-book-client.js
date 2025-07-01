const grpc = require("@grpc/grpc-js");
const protoLoader = require("@grpc/proto-loader");

const packageDef = protoLoader.loadSync("grpc/book.proto", {
  keepCase: true,
  longs: String,
  enums: String,
  defaults: true,
  oneofs: true,
});

const bookProto = grpc.loadPackageDefinition(packageDef).book;

const client = new bookProto.BookService(
  "localhost:9090",
  grpc.credentials.createInsecure()
);

module.exports = client;
