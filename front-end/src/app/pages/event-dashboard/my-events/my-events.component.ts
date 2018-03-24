import { Component, OnInit, Input } from "@angular/core";
import { EagerEvent } from "../../../../eagerEvent";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs/Observable";
import 'rxjs/add/observable/of';
import { AsyncPipe } from "@angular/common";
import {UtilService} from "../../../_services/util/util.service";

@Component({
  selector: "my-events",
  templateUrl: "./my-events.component.html",
  styleUrls: ["./my-events.component.css"]
})
export class MyEventsComponent implements OnInit {
  loadedUpcoming = false;
  loadedPast = false;
  loadedInvited = false;
  pastEvents:any[] = [];
  upcomingEvents:any[] = [];
  invitedEvents:any[] =[];
  starred: Observable<any[]>;
  getDetails = false;
  coordinator:boolean;
  @Input() accountId:number;

  constructor(private http: HttpClient, private utilService:UtilService) {
  }

  ngOnInit() {
    const date = new Date;

    this.http.get<any[]>(
      "http://localhost:8080/api/attendant/getEvents/" +
        localStorage.getItem("token.accountId")
    ).subscribe(events => {
      for (let event in events) {
        let eventDate: Date = new Date(events[event].date);
        console.log(date);
        console.log(eventDate);
        if (eventDate.valueOf() > date.valueOf()) {
          this.upcomingEvents.push(events[event]);
        } else this.pastEvents.push(events[event]);
      }
      if (this.pastEvents.length > 0) this.loadedPast = true;
      if (this.upcomingEvents.length > 0) this.loadedUpcoming = true;
    });

    this.http.get<any>("http://localhost:8080/api/account/getById/"+localStorage.getItem("token.accountId"))
    .subscribe((account) => {
      if (account.role == "ATTENDANT") this.coordinator = false;
      else this.coordinator = true;
    });

    const req = {
      token: {
        id: localStorage.getItem("token.id"),
        accountId: localStorage.getItem("token.accountId")
      },
      object:{}
    }
    this.http.post<any[]>(this.utilService.getServerUrl() + "api/invites/getReceivedInvites",req).subscribe((invites) =>{
      for (let invite in invites) {
        this.invitedEvents.push(invites[invite]);
      }
      if (this.invitedEvents.length > 0) this.loadedInvited = true;
    });
  }

  details(eventId: number) {
    this.getDetails = false;
    this.starred = this.http.get<any[]>(
      "/api/starred/getEventStarred/" +
        eventId +
        "/" +
        localStorage.getItem("token.accountId")
    );
    this.getDetails = true;
  }

  invite(e, eventId:number) {
    e.preventDefault();
    const invite = {
      token: {
        id: localStorage.getItem("token.id"),
        accountId: localStorage.getItem("token.accountId")
      },
      object:{
        eventId:eventId,
        inviter:localStorage.getItem("token.accountId"),
        invitee:e.target[0].value
      }
    }
    this.http.put(this.utilService.getServerUrl() + "api/event/invite", invite).subscribe(response=>{
      console.log(response);
    });
  }

  accept(eventId:number) {
    const invite = {
      invite:{
        id:eventId,
        invitee:localStorage.getItem("token.accountId")
      },
      token:localStorage.getItem("token.id")
    }
    this.http.put("localhost:8080/api/event/acceptInvite", invite)
  }

  ignore(eventId:number) {
    const invite = {
      invite:{
        id:eventId,
        invitee:localStorage.getItem("token.accountId"),
      },
      token:localStorage.getItem("token.id"),
    }
    this.http.put("localhost:8080/api/event/ignoreInvite"+localStorage.getItem("token.accountId"), invite)
  }
}
