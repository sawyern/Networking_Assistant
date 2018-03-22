import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Account} from "../../beans/Account";
import {Observable} from "rxjs/Observable";

@Injectable()
export class GetAccountService {

  constructor(private http: HttpClient) { }

  getAccountByEmail(email : string) : Observable<Account> {
    return this.http.get<Account>('http://localhost:8080/api/account/getByEmail/' + email);
  }

  getAccountById(id : string) : Observable<Account> {
    return this.http.get<Account>('http://localhost:8080/api/account/getById/' + id);
  }
}
