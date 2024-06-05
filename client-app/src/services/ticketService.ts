import CustomFetchService from "./customFetchService";
import { CreateTicketDTO } from "../models/ticketDTOs/CreateTicketDTO";
import { TicketDTO } from "../models/ticketDTOs/TicketDTO";
import {TicketUpgrade} from "../enum/TicketUpgrade";

class TicketService {
    private readonly baseURI: string = "api/ticket";
    private readonly customFetchService: CustomFetchService;

    constructor() {
        this.customFetchService = new CustomFetchService();
    }

    public async getTicketInfo(ticketId: string): Promise<TicketDTO> {
        return await this.customFetchService.get<TicketDTO>(`${this.baseURI}/${ticketId}`);
    }

    public async getMyTickets(): Promise<TicketDTO[]> {
        return await this.customFetchService.get<TicketDTO[]>(`${this.baseURI}/my-tickets`);
    }

    public async bookSeat(movieId: string, createTicketDTO: CreateTicketDTO): Promise<TicketDTO> {
        return await this.customFetchService.post<TicketDTO>(`${this.baseURI}/book-seat/${movieId}`, createTicketDTO);
    }

    public async upgradeTicket(ticketId: string, upgrade: TicketUpgrade): Promise<TicketDTO> {
        const strUpgrade = TicketUpgrade[upgrade];
        return await this.customFetchService.put<TicketDTO>(`${this.baseURI}/${ticketId}/${strUpgrade}`, {});
    }

    public async deleteTicket(ticketId: string): Promise<void> {
        await this.customFetchService.delete(`${this.baseURI}/${ticketId}`);
    }
}

export default TicketService;
