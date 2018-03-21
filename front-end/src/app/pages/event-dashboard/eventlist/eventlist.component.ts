import { Component, OnInit } from '@angular/core';
import { EagerEvent } from "../../../../eagerEvent";
import {Event} from "../../../beans/Event";
import {HttpClient} from "@angular/common/http";
import {UtilService} from "../../../_services/util/util.service";
import {utils} from "protractor";

@Component({
  selector: 'app-eventlist',
  templateUrl: './eventlist.component.html',
  styleUrls: ['./eventlist.component.css']
})
export class EventlistComponent implements OnInit {

  events:Event[];

  constructor(private http:HttpClient, private utilService:UtilService) {
    console.log("Constructed");
    this.getEvents();
  }

  ngOnInit() {
  }

  getEvents(){
    console.log("Retrieving Data");
    this.http.get<any>(this.utilService.getServerUrl() + "api/events").subscribe(response=>{
      this.events = response;
      console.log("Data retrieved");
    });
  }
}
