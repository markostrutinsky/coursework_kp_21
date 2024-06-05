import {PawnItem} from "./PawnItem";

export interface HouseholdGoods extends PawnItem {
    productType: string; // e.g. "kitchenware", "bedding", "appliances"
    brand: string;
    material: string; // e.g. "stainless steel", "glass", "fabric"
    size: number;
    condition: string;
    model: string;
}