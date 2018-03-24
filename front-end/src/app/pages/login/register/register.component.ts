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
  @Input() rpassword1: string;
  @Input() rpassword2: string;

  invalid: boolean = false;
  valid: boolean = false;
  errorMsg: string = "";
  successMsg: string="";

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
    if(this.rpassword1 != this.rpassword2){
      this.invalid = true;
      this.valid = false;
      this.errorMsg = "Passwords do not match."
    }
    else{
      this.registerService.register(this.rfname, this.rlname, this.remail, this.rpassword1)
        .then(data => {
          this.router.navigateByUrl('login');
          this.invalid = false;
          this.errorMsg = "";
          this.valid = true;
          this.successMsg = "Registered Successfully!";
        })
        .catch(
          error => {
            this.invalid = true;
            this.valid = false;
            if (error.status == 400)
              this.errorMsg = "Email already taken";
            else if (error.status >= 500)
              this.errorMsg = "Server not responding. Try again later.";
            else this.errorMsg = "Server not responding. Try again later."
          });
    }
  }
}
