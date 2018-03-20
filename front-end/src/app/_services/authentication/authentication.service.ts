  import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import {catchError} from 'rxjs/operators';
import {of} from 'rxjs/observable/of';
  import {Token} from "../../beans/Token";
  import {Account} from "../../beans/Account";
  import {JsonRequestBody} from "../../beans/JsonRequestBody";

@Injectable()
export class AuthenticationService {

  constructor(private http: HttpClient) {}

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
        attachment: ""
    };
    //return of(obj);
    return this.http.put<Account>('http://localhost:8080/api/register', reqParams)
  }

  serverAuth(email: string, password: string): Observable<Token> {

    let reqParams = {
      object: {
        email: email,
        passwordHash: password
      }
    };

    let obj : Token = {
        id: "1234",
        accountId: "1"
    };
    //return of(obj);
    return this.http.post<Token>('http://localhost:8080/api/login', reqParams)
  }

  serverAuthLogout(): Observable<any> {
    let reqParams = {
      token: {
        id: localStorage.getItem("token.id"),
        accountId: localStorage.getItem("token.accountId")
      },
    };
    //return of({});
    return this.http.post<Token>('http://localhost:8080/api/logout', reqParams)
  }

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

  login(email: string, password: string) : Promise<Token> {
    console.log('authservice.login called!');
    return new Promise<Token>((res, rej) => {
      this.serverAuth(email, password).toPromise()
        .then(token => {
          if (token) {
            console.log('success ' + token);
            localStorage.setItem('token.id', token.id);
            localStorage.setItem('token.accountId', token.accountId);
            // localStorage.setItem('account.firstName', token.);
            res(token);
          } else {
            console.log('error ' + token);
            rej(token);
          }
        }).catch(err => {
        rej(err);
      });
    });
  }

  logout() : Promise<Token> {
    console.log('logout initiated');
    let token : Token;

    let requestBody : JsonRequestBody<any>;

    return new Promise<Token>((res, rej) => {
      this.serverAuthLogout().toPromise()
        .then(() => {
          localStorage.removeItem('token.id');
          localStorage.removeItem('token.accountId');
          res();
        }).catch(() => {
          rej();
      });
    });
  }

  getAccountByEmail(email : string) : Observable<Account> {
    let account = new Account();
    account.email = "sawyer";
    account.firstName = "Sawyer";
    //return of(account);
    return this.http.get<Account>('http://localhost:8080/api/account/getByEmail/' + email);
  }
}
