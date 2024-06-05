import {PawnItem} from "./PawnItem";

export interface Clothes extends PawnItem {
    size: number;
    brand: string;
    clothingType: string; // e.g. "shirt", "pants", "dress"
    material: string; // e.g. "cotton", "polyester", "silk"
    condition: string;
}