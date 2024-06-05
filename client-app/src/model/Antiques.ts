import {PawnItem} from "./PawnItem";

export interface Antiques extends PawnItem {
    age: number;
    originality: string; // e.g. "original", "restored", "reproduction"
    condition: string;
    material: string; // e.g. "wood", "metal", "porcelain"
    maker: string;
    provenance: string;
}