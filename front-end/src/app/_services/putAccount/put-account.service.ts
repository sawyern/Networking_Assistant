import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {UtilService} from "../util/util.service";
import {Observable} from "rxjs/Observable";


@Injectable()
export class PutAccountService {

  constructor(private http: HttpClient, private util : UtilService) { }


  updateAccount (account: Account): Observable<any> {
    return this.http.post(this.util.getServerUrl() + 'api/account/update', account)
  }

}
