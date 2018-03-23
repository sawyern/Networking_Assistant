import { Injectable } from '@angular/core';
import {Account} from "../../../beans/Account";
import {Observable} from "rxjs/Observable";
import {HttpClient} from "@angular/common/http";
import {UtilService} from "../../util/util.service";

@Injectable()
export class RegisterService {

  constructor(private http : HttpClient, private util : UtilService) { }

  register(fname: string, lname: string, email: string, password: string) {
    console.log('authservice.register called!');
    return new Promise<Account>((res, rej) => {
      this.registerAuth(fname, lname, email, password).toPromise()
        .then(account => {
          if (account) {
            console.log('success ' + account);
            res(account);
          } else {
            console.log('error ' + account);
            rej(account);
          }
        }).catch(err => {
        rej(err);
      })
    });
  }

  registerAuth(fname: string, lname: string, email: string, password: string): Observable<Account> {

    let reqParams = {
      object: {
        firstName: fname,
        lastName: lname,
        email: email,
        passwordHash: password
      }
    };

    let obj : Account = {
      id : "1",
      email: "snovak@test.com",
      passwordHash: "password",
      firstName : "sawyer",
      lastName : "novak",
      phone: "808-222-2222",
      background: "I love revature",
      zipCode: "89521",
      attachment: new File()
    };
    //return of(obj);
    return this.http.put<Account>(this.util.getServerUrl() + 'api/register', reqParams)
  }
}
