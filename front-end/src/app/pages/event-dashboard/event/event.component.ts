import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Location} from "../../../beans/Location";
import {Event} from "../../../beans/Event"
import {Attendee} from "../../../beans/Attendee";
import {UtilService} from "../../../_services/util/util.service";
import {Announcement} from "../../../beans/Announcement";
import {GoToService} from "../../../_services/go-to/go-to.service";
@Component({
  selector: 'app-event',
  templateUrl: './event.component.html',
  styleUrls: ['./event.component.css']
})
export class EventComponent implements OnInit {

  event:Event;
  attendees:Attendee[];
  coordinatorId:number;
  coordinatorLogged = false;
  announcements:Announcement[];
  lat: number = 0;
  lng: number = 0;

  constructor(private http:HttpClient, private utilService:UtilService, private goToService:GoToService) {
    this.event = new Event();
    this.event.location = new Location();
    this.event.location.state="";
    this.event.location.city="";
    this.event.location.streetName="";
    this.event.location.addressNum="";
    this.event.location.zip="";
    this.event.id=0;
    this.event.name="";
    this.event.description="";
    this.event.date=new Date();
    this.attendees = [];
  }

  ngOnInit() {
  }

  setEvent(event:Event){
    this.event = event;
    this.getAttendees(event);
    this.getAddress();
    this.getAnnouncements(event);
  }

  getAttendees(event:Event){
    this.attendees = [];
    let url = this.utilService.getServerUrl() + "/api/event/getAttendees/" + event.id;
    this.http.get<any>(url).subscribe(response=>{
      for(let s of response){
        if(s.role == "COORDINATOR") this.coordinatorId = s.accountId;
        url = this.utilService.getServerUrl() + "/api/account/getById/" + s.accountId;
        this.http.get<any>(url).subscribe(response1=>{
          this.attendees.push(response1);
        });
      }
      if(this.coordinatorId == parseInt(localStorage.getItem('token.accountId'))) this.coordinatorLogged = true;
      else this.coordinatorLogged = false;
    })
  }

  goToProfile(e){
    let profile = e.target.attributes[1].value;
    this.goToService.goTo('/profile/'+profile);
  }

  getAnnouncements(event:Event){
    this.announcements = [];
    let url = this.utilService.getServerUrl() + "api/announcements/getByEventId/"+event.id;
    this.http.get<any>(url).subscribe(response=>{
      this.announcements = response;
    });
  }

  putAnnouncement(e){
    e.preventDefault();
    let url = this.utilService.getServerUrl() + "api/announcement/create";
    var eventId = this.event.id;
    let toSend = {
      token: {
        id: localStorage.getItem("token.id"),
        accountId: localStorage.getItem("token.accountId")
      },
      object: {
        eventId:eventId,
        accountId:this.coordinatorId,
        message:e.target[0].value
      }
    };
    console.log(toSend);
    this.http.put<any>(url,toSend).subscribe(response=>{
      console.log(response);
      this.getAnnouncements(this.event);
    });
  }

  getAddress(){
    // 11730 Plaza America Dr #205, Reston, VA 20190
    let streetArr = this.event.location.streetName.split(' ');
    let street = "";
    for(let s of streetArr){
      street += s+"+";
    }
    this.http.get<any>(`https://maps.googleapis.com/maps/api/geocode/json?address=${this.event.location.addressNum}+${street},+${this.event.location.city},
    +${this.event.location.state}&key=AIzaSyBsUeBPaFr-gmdDk-LmZE-nb67aC-5x1Qs`).subscribe(response=>{
        this.lat = response.results[0].geometry.location.lat;
        this.lng = response.results[0].geometry.location.lng;
      }
    );
  }
}
