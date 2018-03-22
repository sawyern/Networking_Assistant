import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  private login: boolean;
  private logout:boolean;
  private tabs:boolean;

  constructor(private router:Router) {
    this.checkPath();
    router.events.subscribe(() => {
      this.checkPath();
    });
  }

  ngOnInit() {
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
