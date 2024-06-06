import {PawnItem, PawnItemCategory} from "./PawnItem";

export interface Jewelry extends PawnItem {
    metalSample: number;
    weight: number;
    size: number;
    isPreciousStones: boolean;
    stonesCount: number;
}

