import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import {catchError} from 'rxjs/operators';
import {of} from 'rxjs/observable/of';

@Injectable()
export class AuthenticationService {

  constructor(private http: HttpClient) { }

  login(email: string, password: string) {
    return this.http.post<any>('/api/login', { email, password })
      .map(user => {
        // login success -- save token to local storage
        if (user && user.token) {
          localStorage.setItem('currentUser', JSON.stringify(user.token));
        }
        // } else {
        //   catchError(err => of(`Error:' ${err}`))
        // }
        return user;
      });
  }

  logout() {
    localStorage.removeItem('currentUser');
  }

}
