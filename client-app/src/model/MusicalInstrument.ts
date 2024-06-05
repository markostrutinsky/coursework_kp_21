import {PawnItem} from "./PawnItem";

export interface MusicalInstrument extends PawnItem {
    instrumentType: string;
    brand: string;
    condition: string;
    age: number;
    model: string;
}
