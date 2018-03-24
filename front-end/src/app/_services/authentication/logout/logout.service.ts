import { Injectable } from '@angular/core';
import {Token} from "../../../beans/Token";
import {Observable} from "rxjs/Observable";
import {Account} from "../../../beans/Account";
import {of} from "rxjs/observable/of";
import {HttpClient} from "@angular/common/http";

@Injectable()
export class LogoutService {

  constructor(private http: HttpClient) { }

  logout() : Promise<Token> {
    console.log('logout initiated');
    console.log(localStorage.getItem('token.id'));
    return new Promise<Token>((res, rej) => {
      this.serverAuthLogout().toPromise()
        .then(() => {
          localStorage.removeItem('token.id');
          localStorage.removeItem('token.accountId');
          res();
          console.log(localStorage.getItem('token.id'));
        }).catch(() => {
        rej();
      });
    });
  }

  serverAuthLogout(): Observable<any> {
    let reqParams = {
      token: {
        id: localStorage.getItem("token.id"),
        accountId: localStorage.getItem("token.accountId")
      },
    };
    return this.http.post<Token>('http://localhost:8080/api/logout', reqParams)
  }
}
