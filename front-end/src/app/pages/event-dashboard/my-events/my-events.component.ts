import { Component, OnInit, Input } from "@angular/core";
import { EagerEvent } from "../../../../eagerEvent";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs/Observable";
import { AsyncPipe } from "@angular/common";

@Component({
  selector: "my-events",
  templateUrl: "./my-events.component.html",
  styleUrls: ["./my-events.component.css"]
})
export class MyEventsComponent implements OnInit {
  pastEvents:any[] = [];
  upcomingEvents:any[] = [];
  invitedEvents:any[] =[];
  starred: Observable<any[]> = undefined;
  getDetails = false;
  coordinator:boolean;
  @Input() accountId:number;

  constructor(private http: HttpClient) {
    const date = new Date().getDate();

    http.get<any[]>(
      "localhost:8080/api/attendant/getEvents/" +
        localStorage.getItem("token.accountId")
    ).subscribe(events => {
      for (let event in events) {
        let eventDate: Date = events[event].date;
        if (eventDate.valueOf() > date.valueOf()) {
          this.upcomingEvents.push(events[event]);
        } else this.pastEvents.push(events[event]);
      }
    });

    http.get<any>("localhost:8080/api/account/getById/"+localStorage.getItem("token.accountId"))
    .subscribe((account) => {
      if (account.role == "attendant") this.coordinator = false;
      else this.coordinator = true;
    });

    const req = {
      token:localStorage.getItem("token.id"),
      invite:{
        id:null,
        inviter:null,
        invitee:localStorage.getItem("token.accountId")
      }
    }
    http.get<any[]>("localhost:8080/api/invites/getReceivedInvites"+localStorage.getItem("token.accountId")).subscribe((invites) =>{
      for (let invite in invites) {
        this.invitedEvents.push(invites[invite]);
      }
    });
  }

  ngOnInit() {}

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

  invite(accountId:number, eventId:number) {
    const invite = {
      id:eventId,
      inviter:localStorage.getItem("token.accountId"),
      invitee:accountId,
      token:localStorage.getItem("token.id")
    }
    this.http.put("localhost:8080/api/event/invite", invite)
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
