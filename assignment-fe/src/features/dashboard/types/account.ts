export interface Account {
    id: string;
    number: string;
    type: string;
    balance: number;
    currency: string;
    issuer: string;
    primary: boolean;
}