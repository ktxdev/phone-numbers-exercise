import axios from "axios";

const AxiosClient = axios.create({
    baseURL: `http://localhost:8080/api/v1`,
    headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
    }
})

export default AxiosClient;