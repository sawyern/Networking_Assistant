  import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import {catchError} from 'rxjs/operators';
import {of} from 'rxjs/observable/of';

@Injectable()
export class AuthenticationService {

  constructor(private http: HttpClient) { }

  serverRegisterAuth(fname: string, lname: string, email: string, password: string): Observable<any> {

    let reqParams = {
      object: {
        first_name: fname,
        last_name: lname,
        email: email,
        passwordHash: password
      }
    }
    return this.http.put<any>('http:/localhost:8080/api/register', reqParams)
  }

  serverAuth(email: string, password: string): Observable<any> {

    let reqParams = {
      object: {
        email: email,
        passwordHash: password
      }
    };

    return this.http.post<any>('http:/localhost:8080/api/login', reqParams)
  }

  register(fname: string, lname: string, email: string, password: string) {
    console.log('authservice.register called!');
    return new Promise<any>((res, rej) => {
      this.serverRegisterAuth(fname, lname, email, password).toPromise()
        .then(token => {
          if (token) {
            console.log('success ' + token);
            localStorage.setItem('token', token);
            res(token);
          } else {
            console.log('error ' + token);
            rej(token);
          }
        })
    });
  }

  login(email: string, password: string) {
    console.log('authservice.login called!');
    return new Promise<any>((res, rej) => {
      this.serverAuth(email, password).toPromise()
        .then(token => {
          if (token) {
            console.log('success ' + token);
            localStorage.setItem('token', token);
            res(token);
          } else {
            console.log('error ' + token);
            rej(token);
          }
        })
    });
  }

  serverAuthLogout(id: string, accountId: string): Observable<any> {

    let reqParams = {
      token: {
        id: localStorage.getItem(id),
        accountId: localStorage.getItem(accountId)
      }
    };
    return this.http.post<any>('http:/localhost:8080/api/logout', reqParams)
  }

  logout(id: string, accountId: string) : Promise<any> {
    console.log('logout initiated');
    return new Promise<any>((res, rej) => {
      this.serverAuthLogout(id, accountId).toPromise()
        .then(() => {
          localStorage.removeItem('token');
          res();
        }).catch(() => {
          rej();
      });
    });
  }

}
