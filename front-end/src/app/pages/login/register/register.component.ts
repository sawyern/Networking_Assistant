import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import  * as $ from 'jquery';
import {ActivatedRoute, Router} from "@angular/router";
import {RegisterService} from "../../../_services/authentication/register/register.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  @Input() rfname: string;
  @Input() rlname: string;
  @Input() remail: string;
  @Input() rpassword: string;

  invalid: boolean = false;
  errorMsg: string = "";

  constructor(private route: ActivatedRoute,
              private router: Router,
              private registerService: RegisterService) {
  }

  ngOnInit() {
  }

  @Output() toggleEvent = new EventEmitter<any>();

  callParent() {
    this.toggleEvent.emit();
  }

  onRegisterSubmit() {
    console.log('onRegisterSubmit called');
    this.registerService.register(this.rfname, this.rlname, this.remail, this.rpassword)
      .then(data => {
        this.router.navigateByUrl('login');
        this.invalid = true;
        this.errorMsg = "";
      })
      .catch(
        error => {
          this.invalid = true;
          if (error.status == 400)
            this.errorMsg = "Email already taken";
          else if (error.status >= 500)
            this.errorMsg = "Server not responding. Try again later.";
          else this.errorMsg = "Server not responding. Try again later."
        });
  }
}
