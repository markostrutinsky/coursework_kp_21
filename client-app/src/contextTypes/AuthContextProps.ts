import {Dispatch, SetStateAction} from "react";
import { AuthUserDTO } from "../services/authService";

export interface AuthContextProps {
    authUser: AuthUserDTO | null
    setAuthUser: Dispatch<SetStateAction<AuthUserDTO | null>>;
}