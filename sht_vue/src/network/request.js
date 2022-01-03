import axios from "axios";

axios.defaults.withCredentials=true;
export function request(config) {
    const request = axios.create({
        baseURL: "http://localhost:8081",
        timeout: 5000,
        withCredentials: true,
    })
    return request(config);
}

