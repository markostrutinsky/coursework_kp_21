import {PawnItem} from "./PawnItem";

export interface Clothes extends PawnItem {
    size: number;
    brand: string;
    clothingType: string; 
    material: string;
    condition: string;
}