import AxiosClient from "../api-client"

export const getAllCustomers = (params) => {
    return AxiosClient.get('/customers', { params })
}