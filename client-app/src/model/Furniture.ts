import {PawnItem} from "./PawnItem";

export interface Furniture extends PawnItem {
    material: string;
    style: string;
    width: number;
    height: number;
    depth: number;
    age: number;
    brand: string;
    condition: string;
}