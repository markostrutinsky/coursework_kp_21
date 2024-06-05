import React, {FC, useState} from 'react';
import './AuthPage.scss';
import AuthService from "../../services/authService";
import {useLocation, useNavigate} from "react-router-dom";
import {useAuth} from "../../hooks/useAuth";

interface AuthPageProps {
}

const AuthPage: FC<AuthPageProps> = (props) => {
    const [isRegistering, setIsRegistering] = useState(true);
    const [fullname, setFullname] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const authService = new AuthService();
    const {setAuthUser} = useAuth();
    const location = useLocation();
    const {from} = location.state as {from: string} || {from: '/movies'};
    const navigate = useNavigate();

    const handleToggle = () => {
        setIsRegistering(!isRegistering);
        setError('');
        setFullname('');
        setEmail('');
        setPassword('');
    };

    const handleRegister = async (event : any) => {
        event.preventDefault();
        try {
            const user = await authService.register({fullName: fullname, email: email, password: password});
            console.log('Registered user:', user);
            localStorage.setItem('authUser', JSON.stringify(user));
            setAuthUser(user);
            navigate(from);
        } catch (err) {
            setError('Registration failed. Please try again.');
        }
    };

    const handleLogin = async (event: any) => {
        event.preventDefault();
        try {
            const user = await authService.login({email, password});
            console.log('Logged in user:', user);
            localStorage.setItem('authUser', JSON.stringify(user));
            setAuthUser(user);
            navigate(from);
        } catch (err) {
            setError('Login failed. Please try again.');
        }
    };

    return (
        <div className="auth-page">
            <div className="auth-container">
                <h2>{isRegistering ? 'Register' : 'Login'}</h2>
                <form onSubmit={isRegistering ? handleRegister : handleLogin}>
                    {isRegistering && (
                        <div className="form-group">
                            <label>Full Name</label>
                            <input
                                type="text"
                                value={fullname}
                                onChange={(e) => setFullname(e.target.value)}
                                required
                            />
                        </div>
                    )}
                    <div className="form-group">
                        <label>Email</label>
                        <input
                            type="email"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            required
                        />
                    </div>
                    <div className="form-group">
                        <label>Password</label>
                        <input
                            type="password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            required
                        />
                    </div>
                    {error && <p className="error">{error}</p>}
                    <button type="submit">{isRegistering ? 'Register' : 'Login'}</button>
                </form>
                <button className="toggle-button" onClick={handleToggle}>
                    {isRegistering ? 'Already have an account? Login' : 'New here? Register'}
                </button>
            </div>
        </div>
    );
}

export default AuthPage;
