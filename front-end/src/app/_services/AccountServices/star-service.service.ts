import { Injectable } from '@angular/core';
import {Observable} from "rxjs/Observable";
import { HttpClient } from "@angular/common/http";
import { UtilService} from "../util/util.service";
import {JsonRequestBody} from "../../beans/JsonRequestBody";
import {Starred} from "../../beans/Starred";
import {ActivatedRoute} from "@angular/router";
import {of} from 'rxjs/observable/of';

@Injectable()
export class StarServiceService {

  constructor(private route: ActivatedRoute,
              private http: HttpClient,
              private util : UtilService) { }

  updateStarred (starredId: string): Observable<Starred> {
    let body : JsonRequestBody<Starred> = new JsonRequestBody();
    let star : Starred;
    star.ownerId = localStorage.getItem('token.accountId');
    star.starredAccountId = starredId;

    body.object = star;
    return this.http.post<Starred>(this.util.getServerUrl() + 'api/account/addStar', body)
  }
  // to remove Star
  // removeStarred (starredId: string): Observable<Starred> {
  //   let body : JsonRequestBody<Starred> = new JsonRequestBody();
  //   let star : Starred;
  //   star.ownerId = localStorage.getItem('token.accountId');
  //   star.starredAccountId = starredId;
  //
  //   body.object = star;
  //   return this.http.delete<Starred>(this.util.getServerUrl() + 'api/account/delete', body)
  // }

  // getStarredAccount(id : string) : Observable<boolean> {
  //   // return of(true);
  //   let body : JsonRequestBody<Starred> = new JsonRequestBody();
  //   body.token = localStorage.getItem('token.accountId');
  //   return this.http.get<boolean>(this.util.getServerUrl() + 'api/account/getStarredAccounts/', body);
  // }

}
