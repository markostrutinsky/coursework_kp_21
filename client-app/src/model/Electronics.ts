import {PawnItem} from "./PawnItem";

export interface Electronics extends PawnItem {
    year: number;
    brand: string;
    model: string;
    productType: string; // e.g. "TV", "smartphone", "laptop"
    screenSize: number;
    storageCapacity: number;
}