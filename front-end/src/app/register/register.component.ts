import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import {GoToService} from "../_services/go-to.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(private router:Router, private goToService: GoToService) { }

  ngOnInit() {
  }

}
