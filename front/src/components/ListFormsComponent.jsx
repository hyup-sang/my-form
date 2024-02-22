import { useEffect, useState } from "react"
import { useNavigate, Link } from "react-router-dom"
import { retrieveAllFormsApi } from "./api/FormsApiService"
import { useAuth } from "./security/AuthContext"

function ListFormsComponent() {
    const authContext = useAuth()
    const [formsList, setFormsList] = useState([])
    const [message, setMessage] = useState(null)
    useEffect (() => refreshForms(), [])
    const navigate = useNavigate()

    function updateForms(id) {
        navigate(`/forms/${id}`)
    }

    function addForms() {
        navigate(`/forms/-1`)
    }

    function refreshForms() {
        retrieveAllFormsApi()
        .then(response => {
            console.log(response.data)
            setFormsList(response.data)
        })
        .catch(error => console.log(error))
    
    }

    // function deleteTodo(id) {
    //     console.log('clicked ' + id)
    //     deleteTodoApi(username, id)
    //     .then(() => {
    //         setMessage(`Delete of todo with id = ${id} successful`)
    //         refreshTodos()
    //     })
    //     .catch(error => console.log(error))
    // }

    return (
        <div className="container">
            {message && <div className="alert alert-warning">{message}</div>}
            <div>
                <table className="table">
                    <thead>
                            <tr>
                                <th>Title</th>
                                <th>Status</th>
                                <th>Access</th>
                                <th>Delete</th>
                            </tr>
                    </thead>
                    <tbody>
                    {
                        formsList.map(
                            forms => (
                                <tr key={forms.id}>
                                    <td>
                                        <Link className="nav-link" to={`/forms/${forms.id}`}>
                                            {forms.title}
                                        </Link>
                                    </td>
                                    <td>{forms.status}</td>
                                    <td>{forms.access}</td>
                                    {/* <td>{forms.done.toString()}</td> */}
                                    {/* <td>{forms.targetDate.toString()}</td> */}
                                    <td>
                                        {/* <button className="btn btn-warning" onClick={() => deleteTodo(todo.id)}> */}
                                        <button className="btn btn-warning">
                                            Delete
                                        </button>
                                    </td>
                                </tr>
                            )
                        )
                    }
                    </tbody>
                </table>
            </div>
                       
            <div className="btn btn-success m-5" onClick={addForms}>Add Forms</div>
        </div>
    )
}

export default ListFormsComponent