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
  }

  ngOnInit() {
  }

  setEvent(event:Event){
    this.event = event;
    this.getAddress();
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
