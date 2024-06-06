import CustomFetchService from './customFetchService';

export interface AuthUserDTO {
    username: string;
    email: string;
    jwtToken: string;
}

export interface CreateUserAccountDTO {
    username: string;
    password: string;
    email: string;
}

export interface LoginUserDTO {
    email: string;
    password: string;
}

class AuthService {
    private readonly baseURI: string = "api/auth";
    private readonly customFetchService: CustomFetchService;

    constructor() {
        this.customFetchService = new CustomFetchService();
    }

    public async register(createdUserDTO: CreateUserAccountDTO): Promise<AuthUserDTO> {
        const response = await this.customFetchService.post<AuthUserDTO>(`${this.baseURI}/register-user`, createdUserDTO, {"Authorization":""});
        return response;
    }

    public async login(loginUserDTO: LoginUserDTO): Promise<AuthUserDTO> {
        const response = await this.customFetchService.post<AuthUserDTO>(`${this.baseURI}/login`, loginUserDTO, {"Authorization":""});
        return response;
    }
}

export default AuthService;