import axios from "axios";

export default axios.create({
    baseURL : "http://localhost:9999/productapi",
    headers : {
        "Content-Type" : "application/json",
    }
});