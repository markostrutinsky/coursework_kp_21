import {MovieDTO} from "../movieDTOs/MovieDTO";
import {SeatDTO} from "../seatDTOs/SeatDTO";
import {Addon} from "../../enum/Addon";


export interface TicketDTO {
    id: string;
    userId: string;
    movieId: string;
    movie: MovieDTO;
    seatId: string;
    seat: SeatDTO;
    addons: Addon[];
    movieDate: Date;
}
