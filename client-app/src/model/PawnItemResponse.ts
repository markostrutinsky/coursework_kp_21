export interface PawnItemResponse {
    id: number;
    name: string;
    category: string;
    photo: string;
    agreement: AgreementResponse;
}

export interface AgreementResponse {
    id: number;
    email: string;
}
