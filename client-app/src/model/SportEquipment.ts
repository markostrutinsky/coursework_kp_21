import {PawnItem} from "./PawnItem";

export interface SportEquipment extends PawnItem {
    sportType: string;
    brand: string;
    equipmentType: string;
    size: number;
    condition: string;
    model: string;
}
