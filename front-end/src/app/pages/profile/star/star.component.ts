import { Component, OnInit } from '@angular/core';
import { Account } from "../../../beans/Account";

@Component({
  selector: 'app-star',
  templateUrl: './star.component.html',
  styleUrls: ['./star.component.css']
})
export class StarComponent implements OnInit {

  currentUserId = localStorage.getItem('token.accountId');
  account: Account;

  constructor() { }

  ngOnInit() {
  }

  toggleStar() {
    console.log('Star toggle!');
  }

}
