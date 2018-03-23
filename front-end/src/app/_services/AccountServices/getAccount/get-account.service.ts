import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Account} from "../../../beans/Account";
import {Observable} from "rxjs/Observable";
import {UtilService} from '../../util/util.service';

@Injectable()
export class GetAccountService {

  constructor(private http: HttpClient, private util : UtilService) { }

  getAccountByEmail(email : string) : Observable<Account> {
    return this.http.get<Account>(this.util.getServerUrl() + '/api/account/getByEmail/' + email);
  }

  getAccountById(id : string) : Observable<Account> {
    return this.http.get<Account>(this.util.getServerUrl() + '/api/account/getById/' + id);
  }
}
