interface UserModel {
    id : string;
    name : string;
    lastName : string;
    mail : string;
    phone : string;
    telegramAlias : string;
    isNPC : boolean;
    roles : Array<string>;
    occupation : string;
    lastAccess : number;
    lastAction : string;
    password : string;
}