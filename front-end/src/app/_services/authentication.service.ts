  import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import {catchError} from 'rxjs/operators';
import {of} from 'rxjs/observable/of';

@Injectable()
export class AuthenticationService {

  constructor(private http: HttpClient) { }

  serverAuth(): Observable<any> {

    let obj = {
      token: {
        id: "1234",
        accountId: "1"
      },
      object: {
        email: "snovak@test.com",
        passwordHash: "password",
        phone: "808-222-2222",
        background: "I love revature",
        zipCode: "89521",
        attachment: ""
      }
    };

    return of(obj);
  }


  login(email: string, password: string) {
    console.log('authservice.login called!');
    return new Promise<any>((res, rej) => {
      this.serverAuth().toPromise()
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



  logout() {
    localStorage.removeItem('currentUser');
  }

}
