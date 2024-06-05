import CustomFetchService from "./customFetchService";
import {CreateUserAccountDTO} from "../models/userDTOs/CreateUserAccountDTO";
import {AuthUserDTO} from "../models/userDTOs/AuthUserDTO";
import {LoginUserDTO} from "../models/userDTOs/LoginUserDTO";

class AuthService {
    private readonly baseURI: string = "api/auth";
    private readonly customFetchService: CustomFetchService;

    constructor() {
        this.customFetchService = new CustomFetchService();
    }

    public async register(createdUserDTO: CreateUserAccountDTO): Promise<AuthUserDTO> {
        return (await this.customFetchService.post<AuthUserDTO>(`${this.baseURI}/sign-up`, createdUserDTO));
    }

    public async login(loginUserDTO: LoginUserDTO): Promise<AuthUserDTO> {
        return (await this.customFetchService.post<AuthUserDTO>(`${this.baseURI}/sign-in`, loginUserDTO));
    }
}

export default AuthService