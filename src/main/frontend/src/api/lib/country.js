import AxiosClient from "../api-client"

export const getAllCountries = () => {
    return AxiosClient.get('/countries');
}