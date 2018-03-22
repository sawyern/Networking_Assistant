import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {LogoutService} from "../../_services/authentication/logout/logout.service";
import {GetAccountService} from "../../_services/getAccount/get-account.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  public firstName : string = "";
  public accountId : string = "";

  constructor(private router:Router, private logoutService: LogoutService, private getAccountService : GetAccountService) {
  }

  getMyName() : Promise<string> {
    return new Promise<string>((resolve, reject) => {
      this.getAccountService.getAccountById(localStorage.getItem("token.accountId")).toPromise().then(account => {
        resolve(account.firstName);
      }).catch(error => {
        reject(error);
      });
    });
  }

  ngOnInit() {
    if (this.isLoggedIn()) {
      this.getMyName().then(name => {
        this.firstName = name;
      });
    }
    //need this to navigate to /profile/{accountId}
    this.accountId = localStorage.getItem('token.accountId');
  }

  onLogoutSubmit() {
    console.log('onLogoutSubmit called');
    this.logoutService.logout()
      .then(data => {
        this.router.navigateByUrl('login');
      })
      .catch(
        error => {
          // change this later
          console.log(error);
        });
  }


  isLoggedIn() : boolean {
    return localStorage.getItem('token.id') != null;
  }

}
