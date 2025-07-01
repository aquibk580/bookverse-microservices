const grpc = require("@grpc/grpc-js");
const protoLoader = require("@grpc/proto-loader");
const User = require("./models/User.js");
const dotenv = require("dotenv");
const connectToMongo = require("./db.js");
const bcrypt = require("bcrypt");

dotenv.config();
const packageDef = protoLoader.loadSync("grpc/user.proto", {});
const proto = grpc.loadPackageDefinition(packageDef).user;

const server = new grpc.Server();
connectToMongo();

server.addService(proto.UserService.service, {
  CreateUser: async (call, callback) => {
    try {
      const { name, email, password } = call.request;
      const hashedPassword = await bcrypt.hash(password, 10);
      const user = await User.create({ name, email, password: hashedPassword });
      callback(null, {
        id: user._id.toString(),
        name: user.name,
        email: user.email,
        password: user.password,
      });
    } catch (err) {
      callback(err);
    }
  },

  GetUser: async (call, callback) => {
    try {
      const users = await User.find();
      if (users.length === 0) {
        return callback(new Error("Users not available"));
      }

      const formatted = users.map((user) => ({
        id: user._id.toString(),
        name: user.name,
        email: user.email,
        password: user.password,
      }));

      callback(null, { users: formatted });
    } catch (err) {
      callback(err);
    }
  },

  DeleteUser: async (call, callback) => {
    try {
      const user = await User.findById({ _id: call.request.id });
      if (!user) return callback(new Error("User not found"));
      await User.findByIdAndDelete({ _id: call.request.id });
      callback(null, {});
    } catch (err) {
      callback(err);
    }
  },
});

server.bindAsync(
  "127.0.0.1:50051",
  grpc.ServerCredentials.createInsecure(),
  () => {
    console.log("🟢 gRPC UserService running at http://localhost:50051");
  }
);
