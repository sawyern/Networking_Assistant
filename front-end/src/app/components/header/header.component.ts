import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService} from "../_services/authentication.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  private login: boolean;
  private logout:boolean;
  private tabs:boolean

  id: string;
  accountId: string;

  constructor(private router:Router, private authService: AuthenticationService) {
    this.checkPath();
    router.events.subscribe(() => {
      this.checkPath();
    });
  }

  getUser(): string {
    console.log(localStorage.getItem('object'));
    if (localStorage.getItem('object') != null) {
      return localStorage.get('object.first_name');
    } else return "";
  }

  ngOnInit() {
  }

  onLogoutSubmit() {
    console.log('onLogoutSubmit called');
    this.authService.logout(this.id, this.accountId)
      .then(data => {
        this.router.navigateByUrl('');
      })
      .catch(
        error => {
          // change this later
          console.log(error);
        });
  }


  checkPath(): void{
    let url = this.router.url;
    if(url=="/login" || url=="/register" || url=="/profile" || url=="/event/dashboard" || url=="/event/new") this.login = false;
      else this.login = true;
    if(url == "/" || url=="/login" || url=="/register") this.logout = false;
      else this.logout = true;
    if(url == "/" || url=="/login" || url=="/register") this.tabs = false;
      else this.tabs = true;
  }

}
