import {PawnItem} from "./PawnItem";

export interface Antiques extends PawnItem {
    age: number;
    originality: string;
    condition: string;
    material: string;
    maker: string;
    provenance: string;
}