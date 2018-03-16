import {Component, Input, OnInit} from '@angular/core';
import { AuthenticationService} from '../_services/authentication.service';
import {ActivatedRoute, Router} from '@angular/router';
import  * as $ from 'jquery';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {
  @Input() email: string;
  @Input() password: string;
  returnUrl: string;

  // incorrectLogin: boolean = false;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private authService: AuthenticationService) {
  }

  ngOnInit() {
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';

    $('.message a').click(function () {
      $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
    });
  }

  onSubmit() {
    console.log('onSubmit called');
    this.authService.login(this.email, this.password)
      .then(data => {
        this.router.navigate([this.returnUrl]);
      })
      .catch(
    error => {
      // change this later
      console.log(error);
    });
  }
}
