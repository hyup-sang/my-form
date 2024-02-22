import {BrowserRouter, Routes, Route, Navigate} from 'react-router-dom'
// import LogoutComponent from './LogoutComponent'
import HeaderComponent from './HeaderComponent'
import ListFormsComponent from './ListFormsComponent'
import ErrorComponent from './ErrorComponent'
// import WelcomeComponent from './WelcomeComponent'
import LoginComponent from './LoginComponent'
import FormsComponent from './FormsComponent'
import AuthProvider, { useAuth } from './security/AuthContext'

import './css/FormsApp.css'

// function AuthenticatedRoute({children}) {
//     const authContext = useAuth()
    
//     if(authContext.isAuthenticated)
//         return children

//     return <Navigate to="/" />
// }

export default function FormsApp() {
    return (
        <div className='FormsApp'>
            <AuthProvider>
            <BrowserRouter>
                <HeaderComponent />            
                <Routes>
                    <Route path='/' element={ <LoginComponent /> } />
                    <Route path='/login' element={ <LoginComponent /> } />
                    <Route path='/forms' element={
                            <ListFormsComponent />
                    } />
                    <Route path='/forms/:id' element={
                            <FormsComponent />
                    } />
                    {/*

                    <Route path='/todos' element={
                        <AuthenticatedRoute>
                            <ListTodosComponent /> 
                        </AuthenticatedRoute>
                    } />

                    <Route path='/todo/:id' element={
                        <AuthenticatedRoute>
                            <TodoComponent /> 
                        </AuthenticatedRoute>
                    } />

                    <Route path='/logout' element={
                        <AuthenticatedRoute>
                            <LogoutComponent /> 
                        </AuthenticatedRoute>
                    } /> */}
                    <Route path='*' element={<ErrorComponent /> } />
                </Routes>
                {/* <FooterComponent /> */}
            </BrowserRouter>
            </AuthProvider>
        </div>
    )
}
