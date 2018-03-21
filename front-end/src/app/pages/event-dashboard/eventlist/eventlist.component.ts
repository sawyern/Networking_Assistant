import { Component, OnInit } from '@angular/core';
import { EagerEvent } from "../../../../eagerEvent";

@Component({
  selector: 'app-eventlist',
  templateUrl: './eventlist.component.html',
  styleUrls: ['./eventlist.component.css']
})
export class EventlistComponent implements OnInit {

  events:EagerEvent[];

  constructor() {
    var event1 = new EagerEvent;
    event1.name = "Party";
    var event2 = new EagerEvent;
    event2.name = "Meetup";
    var event3 = new EagerEvent;
    event3.name = "Job Fair";
    var event4 = new EagerEvent;
    event4.name = "Event";
    this.events = [event1,event2,event3,event4];
  }

  ngOnInit() {
  }

}
