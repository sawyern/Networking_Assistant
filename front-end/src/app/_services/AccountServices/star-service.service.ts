import { Injectable } from '@angular/core';
import {Observable} from "rxjs/Observable";
import { HttpClient } from "@angular/common/http";
import { UtilService} from "../util/util.service";
import {JsonRequestBody} from "../../beans/JsonRequestBody";
import {Starred} from "../../beans/Starred";
import {ActivatedRoute} from "@angular/router";
import {of} from 'rxjs/observable/of';
import {Token} from "../../beans/Token";
import {Account} from "../../beans/Account";

@Injectable()
export class StarServiceService {

  constructor(private route: ActivatedRoute,
              private http: HttpClient,
              private util : UtilService) { }

  updateStarred (accountId : string): Observable<Account> {
    let body : JsonRequestBody<Account> = new JsonRequestBody();

    let token : Token = new Token();
    token.id = localStorage.getItem('token.id');
    token.accountId = localStorage.getItem('token.accountId');

    body.token = token;
    let account : Account = new Account();
    account.id = accountId;
    body.object = account;

    return this.http.put<Account>(this.util.getServerUrl() + 'api/account/addStar', body)
  }

  //to remove Star
  deleteStarredAccount (accountId : string): Observable<Account> {
    let body : JsonRequestBody<Account> = new JsonRequestBody();

    let token : Token = new Token();
    token.id = localStorage.getItem('token.id');
    token.accountId = localStorage.getItem('token.accountId');

    body.token = token;
    let account : Account = new Account();
    account.id = accountId;

    return this.http.post<Account>(this.util.getServerUrl() + 'api/account/deleteStar', body)
  }

  isStarredById(id : string) : Observable<Account> {
    let accountId: string;
    let body : JsonRequestBody<any> = new JsonRequestBody();
    let star : any;

    let token : Token = new Token();
    token.id = localStorage.getItem('token.id');
    token.accountId = localStorage.getItem('token.accountId');
    // star.ownerId = localStorage.getItem('token.accountId');

    body.token = token;
    return this.http.post<Account>(this.util.getServerUrl() + 'api/account/isStarredById/' + id, body);
  }

}
