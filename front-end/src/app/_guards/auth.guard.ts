import {EventEmitter, Injectable, Output} from '@angular/core';
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router} from '@angular/router';
import { Observable } from 'rxjs/Observable';
import {LoginService} from "../_services/authentication/login/login.service";

@Injectable()
export class AuthGuard implements CanActivate {

  // @Output()
  // notLogged:EventEmitter<any> = new EventEmitter<any>();

  constructor(private router:Router, private loginService:LoginService){}
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
      if(localStorage.getItem('token.id') != null){
        return true;
    }
    else{
        this.router.navigate(['login']);
        // this.notLogged.emit();
        return false;
      }
  }
}
