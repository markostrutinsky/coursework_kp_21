import {TicketDTO} from "../ticketDTOs/TicketDTO";

export interface UserDTO {
    id: string;
    email: string;
    fullName: string;
    password: string;
    isAdmin: boolean;
    tickets: TicketDTO[];
}
