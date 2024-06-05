import {SeatDTO} from "../seatDTOs/SeatDTO";

export interface MovieDTO {
    id: string;
    name: string;
    genre: string;
    filmDuration: string;
    director: string;
    seats: SeatDTO[];
}