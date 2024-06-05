import {FC} from "react";
import {useAuth} from "../../hooks/useAuth";
import {Outlet} from "react-router-dom";

interface AuthAdminRequiredComponentProps {
}

const AuthAdminRequired: FC<AuthAdminRequiredComponentProps> = (props) => {
    const authUser = useAuth().authUser;
    
    if (authUser && authUser.user.isAdmin) {
        return <Outlet/>;
    }
    return <>Page is forbidden for non-admin users.</>;
}

export default AuthAdminRequired;