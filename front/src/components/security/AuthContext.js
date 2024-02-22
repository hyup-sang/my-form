import { createContext, useContext, useState } from "react";
import { executeJwtAuthenticationService } from "../api/AuthenticationApiService";
import { apiClient } from "../api/ApiClient";

export const AuthContext = createContext()

export const useAuth = () => useContext(AuthContext)

export default function AuthProvider({ children }) {
    const [isAuthenticated, setAuthenticated] = useState(false)
    const [memberId, setMemberId] = useState(null)
    const [nickname, setNickname] = useState(null)
    const [email, setEmail] = useState(null)
    const [token, setToken] = useState(null)

    async function login(email, password) {
        try {
            const response = await executeJwtAuthenticationService(email, password)

            if (response.status === 200) {
                const { token, memberId, nickname, email } = response.data;
                const jwtToken = `Bearer ${token}`;
                setAuthenticated(true);
                setMemberId(memberId);
                setNickname(nickname);
                setEmail(email);
                setToken(jwtToken);

                apiClient.interceptors.request.use(
                    (config) => {
                        console.log('intercepting and adding a token')
                        config.headers.Authorization = jwtToken
                        return config
                    }
                )

                return true
            } else {
                logout()
                return false
            }    
        } catch (e) {
            logout()
            return false
        }
    }

    function logout() {
        setAuthenticated(false)
        setToken(null)
        setMemberId(null)
        setNickname(null)
        setEmail(null)
    }

    return (
        <AuthContext.Provider value={ {isAuthenticated, login, logout, memberId, token}  }>
            {children}
        </AuthContext.Provider>
    )
} 