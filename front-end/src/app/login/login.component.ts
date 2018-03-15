import {Component, Input, OnInit} from '@angular/core';
import { AuthenticationService} from '../_services/authentication.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {

  email : string;
  password : string;

  returnUrl: string;

  // incorrectLogin: boolean = false;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private authService: AuthenticationService
  ) {}

  ngOnInit() {
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  login() {
    // this.authService.login(this.email, this.password)
    //   .subscribe(
    //     data => {
    //       this.router.navigate([this.returnUrl]);
    //     },
    //     error => {
    //       // change this later
    //       console.log(error);
    //     });
  }
}
