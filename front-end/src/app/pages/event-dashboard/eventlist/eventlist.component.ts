import { Component, OnInit } from '@angular/core';
import {Event} from "../../../beans/Event";
import {HttpClient} from "@angular/common/http";
import {UtilService} from "../../../_services/util/util.service";

@Component({
  selector: 'app-eventlist',
  templateUrl: './eventlist.component.html',
  styleUrls: ['./eventlist.component.css']
})
export class EventlistComponent implements OnInit {

  events:Event[];

  constructor(private http:HttpClient, private utilService:UtilService) {
    this.getEvents();
  }

  ngOnInit() {
  }

  getEvents(){
    this.http.get<any>(this.utilService.getServerUrl() + "api/events/getAll").subscribe(response=>{
      this.events = response;
    });
  }
}
