import CustomFetchService from './customFetchService';
import {PawnItem, PawnItemCategory} from "../model/PawnItem";
import {PawnItemResponse} from "../model/PawnItemResponse";

class PawnshopService {
    private readonly baseURI: string = "api/pawnshop";
    private readonly customFetchService: CustomFetchService;

    constructor() {
        this.customFetchService = new CustomFetchService();
    }

    public async getAllByCategory(category: PawnItemCategory): Promise<PawnItem[]> {
        const response = await this.customFetchService.get<PawnItem[]>(`${this.baseURI}/items/by-category/${PawnItemCategory[category]}`);
        return response;
    }

    public async getAll(): Promise<PawnItem[]> {
        const response = await this.customFetchService.get<PawnItem[]>(`${this.baseURI}/all-items`);
        return response;
    }

    public async addItem(formData: Record<string, string>): Promise<PawnItemResponse> {
        const response = await this.customFetchService.post<PawnItemResponse>(`${this.baseURI}/add-item`, formData);
        return response;
    }

    public async getAllByUserId(userId: number){
        const response = await this.customFetchService.get<PawnItem[]>(`${this.baseURI}/all-items/${userId}`);
        return response;
    }

    public async delete(itemId: number){
        const response = await this.customFetchService.delete(`${this.baseURI}/${itemId}`);
        return response;
    }
}

export default PawnshopService;
