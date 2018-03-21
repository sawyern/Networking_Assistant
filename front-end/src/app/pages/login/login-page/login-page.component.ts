import {Component, OnInit, ViewChild} from '@angular/core';
import  * as $ from 'jquery';
import {LoginComponent} from "../login/login.component";
import {RegisterComponent} from "../register/register.component";

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {

  @ViewChild('login') loginComp : LoginComponent;
  @ViewChild('register') registerComp : RegisterComponent;

  constructor() { }

  ngOnInit() {
  }

  public globalToggle(eventParam : string) {
    //$('form').animate({height: "toggle", opacity: "toggle"}, "slow");
    //$('app-register form').animate({height: "toggle", opacity: "toggle"}, "slow");
  }
}
