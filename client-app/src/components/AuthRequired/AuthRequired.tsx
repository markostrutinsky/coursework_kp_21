import React, {FC} from 'react';
import {Navigate, Outlet, useLocation} from "react-router-dom";
import {useAuth} from "../../hooks/useAuth";

interface RequireAuthForEmployersPagesProps {
}

const RequireAuthForEmployersPages: FC<RequireAuthForEmployersPagesProps> = () => {
    const location = useLocation();
    const {authUser} = useAuth();

    console.log(authUser);
    return (
        authUser != null ? (
                <Outlet/>
        ) : (
            <Navigate to="/auth-page" state={{from: location}} replace/>
        ));
}

export default RequireAuthForEmployersPages;