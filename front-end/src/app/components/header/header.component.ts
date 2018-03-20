import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService} from "../../_services/authentication/authentication.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  private login: boolean;
  private logout:boolean;
  private tabs:boolean
  private firstName : string;

  constructor(private router:Router, private authService: AuthenticationService) {
  }

  setUser() {
    this.authService.getAccountByEmail("").subscribe( account => {
      this.firstName = account.firstName;
      console.log("firstname " + account.firstName);
    });
  }

  ngOnInit() {
    this.setUser();
  }

  onLogoutSubmit() {
    console.log('onLogoutSubmit called');
    this.authService.logout()
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
