import dotenv from "dotenv";
import express from "express";
import sessions from "express-session";
import bodyParser from "body-parser";
import cookieParser from "cookie-parser";

const app = express();
app.use(sessions({
    secret: process.env.SESSION_SECRET,
    cookie: { maxAge: 24 * 60 * 60 * 1000 },
    saveUninitialized: true,
    resave: false
}));
app.use(bodyParser.json());
app.use(cookieParser());

app.get("/", async (req, res) => {
    res.send("OK");
});

app.listen(3000);