import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import {GoToService} from "../_services/go-to.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private router:Router, private goToSerivce:GoToService) {
  }

  ngOnInit() {
  }

  goToRegister(){
    this.router.navigate(['register']);
  }
}
