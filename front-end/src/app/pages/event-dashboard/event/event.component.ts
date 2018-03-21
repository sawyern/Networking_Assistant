import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Location} from "../../../beans/Location";
import {Event} from "../../../beans/Event"
import {Attendee} from "../../../beans/Attendee";
@Component({
  selector: 'app-event',
  templateUrl: './event.component.html',
  styleUrls: ['./event.component.css']
})
export class EventComponent implements OnInit {

  event:Event;
  lat: number = 0;
  lng: number = 0;

  constructor(private http:HttpClient) {
    let event = new Event();
    event.description = "An Event";
    let attendee1 = new Attendee();
    attendee1.name = "Joe";
    let attendee2 = new Attendee();
    attendee2.name = "Sawyer";
    let attendee3 = new Attendee();
    attendee3.name = "Jason";
    let attendees:Attendee[];
    attendees = [attendee1,attendee2,attendee3];
    event.name="Event";

    let loc : Location = new Location();
    loc.addressNum = "11730";
    loc.streetName = "Plaza America Dr";
    loc.city = "Reston";
    loc.state = "VA";
    loc.zip = "11111"
    event.location = loc;
    this.event=event;
    this.getAddress();
  }

  ngOnInit() {
  }

  getAddress(){
    // 11730 Plaza America Dr #205, Reston, VA 20190
    this.http.get<any>("https://maps.googleapis.com/maps/api/geocode/json?address=11730+Plaza+America+Dr,+Reston,+VA&key=AIzaSyBsUeBPaFr-gmdDk-LmZE-nb67aC-5x1Qs").subscribe(response=>{
        this.lat = response.results[0].geometry.location.lat;
        this.lng = response.results[0].geometry.location.lng;
      }
    );
  }
}
