import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {EventComponent} from "../event/event.component";
import {GoToService} from "../../../_services/go-to/go-to.service";

@Component({
  selector: 'app-event-dashboard',
  templateUrl: './event-dashboard.component.html',
  styleUrls: ['./event-dashboard.component.css']
})
export class EventDashboardComponent implements OnInit {

  @ViewChild(EventComponent) event:EventComponent;

  constructor(private goToService:GoToService) { }

  updateEvent(e){
    this.event.setEvent(e);
  }

  ngOnInit() {
  }

}
