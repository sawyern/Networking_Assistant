import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {UtilService} from "../../util/util.service";
import {Observable} from "rxjs/Observable";
import {Account} from "../../../beans/Account";
import {JsonRequestBody} from "../../../beans/JsonRequestBody";
import {Token} from "../../../beans/Token";


@Injectable()
export class PutAccountService {

  constructor(private http: HttpClient, private util : UtilService) { }


  updateAccount (account: Account): Observable<Account> {
    let body : JsonRequestBody<Account> = new JsonRequestBody();
    let token : Token = new Token();
    token.id = localStorage.getItem('token.id');
    token.accountId = localStorage.getItem('token.accountId');
    body.object = account;
    body.token = token;
    return this.http.post<Account>(this.util.getServerUrl() + 'api/account/update', body)
  }


}
