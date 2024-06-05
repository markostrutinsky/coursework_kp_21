export interface PawnItem {
    id: number;
    pawnItemName: string;
    category: PawnItemCategory;
    photo: Blob;
    agreementId: number;
}

export enum PawnItemCategory {
    JEWELRY,
    ANTIQUES,
    FURNITURE,
    ELECTRONICS,
    SPORTSEQUIPMENT,
    CLOTHES,
    MUSICALINSTRUMENTS,
    HOUSEHOLDGOODS
}