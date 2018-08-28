interface AppUserModel {
    id : string;
	alias : string;
	level : number;
	type_template : string;
	role : string;
	missions : Array<string>;
	mail : string;
	phone : string;
	telegramAlias : string;
	position : string;
	friends : Array<string>;
}