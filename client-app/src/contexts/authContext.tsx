import {createContext, ReactNode, useEffect, useState} from "react";
import {AuthUserDTO} from "../models/userDTOs/AuthUserDTO";
import {AuthContextProps} from "../contextTypes/AuthContextProps";

const AuthContext = createContext<AuthContextProps>({
    authUser: null,
    setAuthUser: () => {
    }
});

export function AuthProvider({children}: { children: ReactNode; }) {
    const [authUser, setAuthUser] = useState<AuthUserDTO | null>(null);
    useEffect(() => {
        const authUser = localStorage.getItem("authUser");
        if (authUser != null) {
            setAuthUser(JSON.parse(authUser));
        }
    }, []);
    
    return (
        <AuthContext.Provider value={{authUser, setAuthUser}}>
            {children}
        </AuthContext.Provider>
    );
}

export default AuthContext;