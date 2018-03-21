import {Component, OnInit} from '@angular/core';
import {Input} from "@angular/core";
import {Event} from "../../../beans/Event";
import {Location} from "../../../beans/Location";

@Component({
  selector: 'app-event-form',
  templateUrl: 'event-form.component.html',
  styleUrls: ['event-form.component.css']
})
export class EventFormComponent implements OnInit {

  invalid : boolean = false;
  success : boolean = false;
  errMsg : string;

  @Input() eventName : string;
  @Input() date : Date;
  @Input() addressNum : string;
  @Input() streetName : string;
  @Input() city : string;
  @Input() state : string;
  @Input() zip : string;
  @Input() description : string;

  onSubmit() {
    let event = new Event();
    event.name = this.eventName;
    let loc = new Location();
    loc.addressNum = this.addressNum;
    loc.streetName = this.streetName;
    loc.city = this.city;
    loc.state = this.state;
    loc.zip = this.zip;

    event.location = loc;
    event.date = this.date;
    // this.submitService.submitRequest(this.cookie.get("username"), this.cost, event, gradingDetail, optionalItem).then(response => {
    //   this.invalid = false;
    //   this.success = true;
    // }).catch(errorMsg => {
    //   this.router.navigateByUrl("/events/loadError");
    // });
  }

  constructor() {
  }
  ngOnInit() {
  }
}

