import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {LoginService} from "../../../_services/authentication/login/login.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {
  @Input() email: string;
  @Input() password: string;

  invalid: boolean = false;
  errorMsg: string = "";

  id: string;
  accountId: string;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private loginService: LoginService) {
  }

  ngOnInit() {
  }

  @Output() toggleEvent = new EventEmitter<any>();

  callParent() {
    this.toggleEvent.emit();
  }

  onLoginSubmit() {
    console.log('onLoginSubmit called');
    this.loginService.login(this.email, this.password)
      .then(data => {
        this.router.navigateByUrl('');
        this.invalid = true;
        this.errorMsg = "";
      })
      .catch(
        error => {
          this.invalid = true;
          if (error.status >= 400 && error.status < 500)
            this.errorMsg = "Email and password do not match.";
          else if (error.status >= 500)
            this.errorMsg = "Server not responding. Try again later.";
          else this.errorMsg = "Server not responding. Try again later."
        });
  }
}

