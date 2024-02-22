import { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { useAuth } from './security/AuthContext'

function LoginComponent() {
    const [email, setEmail] = useState('test1@testom')
    const [password, setPassword] = useState('qlalfqjsgh1!')
    const [showErrorMessage, setShowErrorMessage] = useState(false)
    const navigate = useNavigate();
    const authContext = useAuth();

    function handleEmailChange(event) {
        setEmail(event.target.value)
    }
    
    function handlePasswordChange(event) {
        setPassword(event.target.value)
    }

    async function handleSubmit() {
        if (await authContext.login(email, password)){
            navigate(`/forms`)
        } else {
            setShowErrorMessage(true)
        }
    }

    return (
        <div className="Login">
            <h1>Login</h1>
            {showErrorMessage && <div className="errorMessage">Authentication Failed. Please check your credentials.</div>}

            <div className="LoginForm">
                <div>
                    <label>Email:</label>
                    <input type="text" name="email" value={email} onChange={handleEmailChange} />
                </div>
                <div>
                    <label>Password:</label>
                    <input type="password" name="password" value={password} onChange={handlePasswordChange} />
                </div>
                <div>
                    <button type="button" name="login" onClick={handleSubmit}>login</button>
                </div>
            </div>
        </div>
    )
}

export default LoginComponent
