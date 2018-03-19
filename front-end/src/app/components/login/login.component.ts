import {Component, Input, OnInit} from '@angular/core';
import { AuthenticationService} from '../_services/authentication.service';
import {ActivatedRoute, Router} from '@angular/router';
import  * as $ from 'jquery';
import { GoToService } from "../_services/go-to.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {
  @Input() email: string;
  @Input() password: string;

  @Input() rfname: string;
  @Input() rlname: string;
  @Input() remail: string;
  @Input() rpassword: string;

  tokenid = localStorage.getItem('token')

  id: string;
  accountId: string;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private authService: AuthenticationService) {
  }

  ngOnInit() {

    //jquery login animation - may refactor later
    $('.message a').click(function () {
      $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
    });
  }

  onLoginSubmit() {
    console.log('onLoginSubmit called');
    this.authService.login(this.email, this.password)
      .then(data => {
        this.router.navigateByUrl('');
      })
      .catch(
    error => {
      // change this later
      console.log(error);
    });
  }

onRegisterSubmit() {
  console.log('onRegisterSubmit called');
  this.authService.register(this.rfname, this.rlname, this.remail, this.rpassword)
    .then(data => {
      this.router.navigateByUrl('');
    })
    .catch(
      error => {
        // change this later
        console.log(error);
      });
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

}


