import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {EventComponent} from "../event/event.component";

@Component({
  selector: 'app-event-dashboard',
  templateUrl: './event-dashboard.component.html',
  styleUrls: ['./event-dashboard.component.css']
})
export class EventDashboardComponent implements OnInit {

  @ViewChild(EventComponent) event:EventComponent;

  constructor() { }

  updateEvent(e){
    this.event.setEvent(e);
  }

  ngOnInit() {
  }

}
