import {Dispatch, SetStateAction} from "react";
import {AuthUserDTO} from "../models/userDTOs/AuthUserDTO";

export interface AuthContextProps {
    authUser: AuthUserDTO | null
    setAuthUser: Dispatch<SetStateAction<AuthUserDTO | null>>;
}