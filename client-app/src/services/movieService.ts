import CustomFetchService from "./customFetchService";
import { CreateMovieDTO } from "../models/movieDTOs/CreateMovieDTO";
import { MovieDTO } from "../models/movieDTOs/MovieDTO";
import {SeatDTO} from "../models/seatDTOs/SeatDTO";

class MovieService {
    private readonly baseURI: string = "api/movie";
    private readonly customFetchService: CustomFetchService;

    constructor() {
        this.customFetchService = new CustomFetchService();
    }

    public async getAllMovies(): Promise<MovieDTO[]> {
        return await this.customFetchService.get<MovieDTO[]>(`${this.baseURI}/all/movies`);
    }

    public async getSeatsByMovieId(movieId: string): Promise<SeatDTO[]> {
        return await this.customFetchService.get<SeatDTO[]>(`${this.baseURI}/available-seats/${movieId}`);
    }

    public async getMovieInfo(movieId: string): Promise<MovieDTO> {
        return await this.customFetchService.get<MovieDTO>(`${this.baseURI}/${movieId}`);
    }

    public async addMovie(createMovieDTO: CreateMovieDTO): Promise<MovieDTO> {
        return await this.customFetchService.post<MovieDTO>(`${this.baseURI}/AddMovie`, createMovieDTO);
    }

    public async removeMovie(movieId: string): Promise<void> {
        await this.customFetchService.delete(`${this.baseURI}/RemoveMovie/${movieId}`);
    }
}

export default MovieService;
