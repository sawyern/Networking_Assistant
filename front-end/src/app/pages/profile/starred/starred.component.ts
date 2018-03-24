import { Component, OnInit, Input } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs/Observable";
import { AsyncPipe } from "@angular/common";
import { UtilService } from "../../../_services/util/util.service";
import { RouterLink } from "@angular/router";
import { JsonRequestBody } from "../../../beans/JsonRequestBody";
import { Token } from "../../../beans/Token";
import { Account } from "../../../beans/Account";

@Component({
  selector: "starred",
  templateUrl: "./starred.component.html",
  styleUrls: ["./starred.component.css"]
})
export class StarredComponent implements OnInit {
  readonly url = this.utils.getServerUrl();
  loadedStarred = false;
  starredAccounts = [];
  details = false;
  constructor(private http: HttpClient, private utils: UtilService) {}

  ngOnInit() {
    let account: Account = new Account();
    let body: JsonRequestBody<Account> = new JsonRequestBody();
    let token: Token = new Token();
    token.id = localStorage.getItem("token.id");
    token.accountId = localStorage.getItem("token.accountId");
    account.id = token.accountId;
    body.object = account;
    body.token = token;
    this.http
      .post(
        this.url +
          "api/account/getStarredAccounts",
        body
      )
      .subscribe(accounts => {
        for (let account in accounts) {
          this.starredAccounts.push(accounts[account]);
        }
        this.loadedStarred = true;
        console.log(this.starredAccounts);
      });
  }

  accountInfo(id: number) {
    this.details = true;
  }
}
