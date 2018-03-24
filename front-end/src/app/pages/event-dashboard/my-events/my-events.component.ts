import { Component, OnInit, Input } from "@angular/core";
import { EagerEvent } from "../../../../eagerEvent";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs/Observable";
import 'rxjs/add/observable/of';
import { AsyncPipe } from "@angular/common";
import {UtilService} from "../../../_services/util/util.service";
import {Account} from "../../../beans/Account";

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
  starred:any[] = [];
  attendees:any[] = [];
  getDetails = false;
  coordinator:boolean;
  @Input() accountId:number;

  constructor(private http: HttpClient, private utilService:UtilService) {
  }

  ngOnInit() {
    this.getEvents();

    this.http.get<any>(this.utilService.getServerUrl() + "api/account/getById/"+localStorage.getItem("token.accountId"))
    .subscribe((account) => {
      if (account.role == "ATTENDANT") this.coordinator = false;
      else this.coordinator = true;
    });

    this.getInvites();
  }

  getEvents(){
    const date = new Date;
    this.http.get<any[]>(
      this.utilService.getServerUrl() + "api/attendant/getEvents/" +
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
  }

  getInvites(){
    this.invitedEvents = [];
    const req = {
      token: {
        id: localStorage.getItem("token.id"),
        accountId: localStorage.getItem("token.accountId")
      },
      object:{}
    }
    this.http.post<any[]>(this.utilService.getServerUrl() + "api/invites/getReceivedInvites",req).subscribe((invites) =>{
      console.log(invites);
      for (let invite in invites) {
        this.invitedEvents.push(invites[invite]);
      }
      if (this.invitedEvents.length > 0) this.loadedInvited = true;
    });
  }

  details(eventId: number) {
    this.starred = [];
    this.attendees = [];
    let url = this.utilService.getServerUrl() + "/api/event/getAttendees/" + eventId;
    this.http.get<any>(url).subscribe(response=>{
      this.starred = response;
      for(let s of this.starred){
        url = this.utilService.getServerUrl() + "/api/account/getById/" + s.accountId;
        this.http.get<any>(url).subscribe(response1=>{
          this.attendees.push(response1);
        });
      }
    });
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
      token: {
        id: localStorage.getItem("token.id"),
        accountId: localStorage.getItem("token.accountId")
      },
      object:{
        eventId:eventId,
        invitee:localStorage.getItem("token.accountId")
      }
    };
    this.http.put(this.utilService.getServerUrl() + "api/account/acceptInvite", invite).subscribe(response=>{
      console.log(response);
      this.getInvites();
      this.getEvents();
    });
  }

  ignore(eventId:number) {
    const invite = {
      token: {
        id: localStorage.getItem("token.id"),
        accountId: localStorage.getItem("token.accountId")
      },
      object:{
        eventId:eventId,
        invitee:localStorage.getItem("token.accountId")
      }
    };
    this.http.post(this.utilService.getServerUrl() + "api/account/ignoreInvite", invite).subscribe(response=>{
      console.log(response);
      this.getInvites();
      this.getEvents();
    });
  }
}
