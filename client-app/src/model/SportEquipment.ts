import {PawnItem} from "./PawnItem";

export interface SportEquipment extends PawnItem {
    sportType: string; // e.g. "soccer", "basketball", "tennis"
    brand: string;
    equipmentType: string; // e.g. "ball", "shoes", "racket"
    size: number;
    condition: string;
    model: string;
}
