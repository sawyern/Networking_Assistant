import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Token} from "../../../beans/Token";
import {Observable} from "rxjs/Observable";
import {UtilService} from "../../util/util.service";

@Injectable()
export class LoginService {

  constructor(private http: HttpClient, private util : UtilService) {
  }

  public login(email: string, password: string): Promise<Token> {
    console.log('authservice.login called');
    return new Promise<Token>((res, rej) => {
      this.loginPostRequest(email, password).toPromise()
        .then(token => {
          if (token) {
            console.log('success ' + token);
            localStorage.setItem('token.id', token.id);
            localStorage.setItem('token.accountId', token.accountId);
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

  private loginPostRequest(email: string, password: string): Observable<Token> {
    let reqParams = {
      object: {
        email: email,
        passwordHash: password
      }
    };
    return this.http.post<Token>(this.util.getServerUrl() +  'api/login', reqParams)
  }
}
