import { apiClient } from './ApiClient'

export const retrieveAllFormsApi
    = () => apiClient.get(`/forms`)

export const retrieveFormsApi
    = (id) => apiClient.get(`/forms/${id}`)

// export const updateTodoApi
//     = (username, id, todo) => apiClient.put(`/users/${username}/todos/${id}`, todo)

// export const createTodoApi
//     = (username,  todo) => apiClient.post(`/users/${username}/todos`, todo)

// export const deleteTodoApi
//     = (username, id) => apiClient.delete(`/users/${username}/todos/${id}`)