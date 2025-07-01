const grpc = require("@grpc/grpc-js");
const protoLoader = require("@grpc/proto-loader");

// Load the user.proto definition
const packageDef = protoLoader.loadSync("grpc/user.proto", {
  keepCase: true,
  longs: String,
  enums: String,
  defaults: true,
  oneofs: true,
});

// Load the gRPC package definition
const userProto = grpc.loadPackageDefinition(packageDef).user;

const client = new userProto.UserService(
  "127.0.0.1:50051", 
  grpc.credentials.createInsecure()
);

module.exports = client;
