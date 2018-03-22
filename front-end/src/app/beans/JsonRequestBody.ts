import {Token} from "./Token";

export class JsonRequestBody<T> {
  token : Token;
  object : T;
  constructor(){}
}
