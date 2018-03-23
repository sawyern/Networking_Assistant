import { Component, OnInit } from "@angular/core";
import { EagerEvent } from "../../../../eagerEvent";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs/Observable";
import { AsyncPipe } from '@angular/common';

@Component({
  selector: "my-events",
  templateUrl: "./my-events.component.html",
  styleUrls: ["./my-events.component.css"]
})
export class MyEventsComponent implements OnInit {
  events: Observable<any[]> = undefined;
  starred: Observable<any[]> = undefined;
  getDetails = false;
  constructor(private http: HttpClient) {
      this.events = http.get<any[]>("localhost:8080/api/attendant/getEvents/"+localStorage.getItem('token.accountId'));
  }

  ngOnInit() {}

  details(eventId:number) {
    this.getDetails = false;
    this.starred = this.http.get<any[]>("/api/starred/getEventStarred/"+eventId+"/"+localStorage.getItem('token.accountId'));
    this.getDetails = true;
  }
}
