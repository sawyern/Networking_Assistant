import {Component, OnInit} from '@angular/core';
import {Input} from "@angular/core";
import {Event} from "../../../beans/Event";
import {Location} from "../../../beans/Location";
import {HttpClient} from "@angular/common/http";
import {UtilService} from "../../../_services/util/util.service";
import {GoToService} from "../../../_services/go-to/go-to.service";

@Component({
  selector: 'app-event-form',
  templateUrl: 'event-form.component.html',
  styleUrls: ['event-form.component.css']
})
export class EventFormComponent implements OnInit {

  invalid : boolean = false;
  success : boolean = false;
  errorMsg : string;

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
    event.description = this.description;
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

    let locationString = this.addressNum + "+" + this.streetName + ",+" + this.city + ",+" + this.state;
    this.http.get<any>(`https://maps.googleapis.com/maps/api/geocode/json?address=${locationString}&key=AIzaSyBsUeBPaFr-gmdDk-LmZE-nb67aC-5x1Qs`).subscribe(response=>{
        if(response.status == "ZERO_RESULTS"){
          this.invalid = true;
          this.success = false;
          this.errorMsg = "Invalid Location. Please try again";
        }
        else{
          this.success = true;
          this.invalid = false;
          let toSend = {
            token: {
              id: localStorage.getItem("token.id"),
              accountId: localStorage.getItem("token.accountId")
            },
            object:event
          };
          this.http.put(this.utilService.getServerUrl()+"api/event/create",toSend).subscribe(
            success=>{
              console.log(success);
              this.invalid = false;
              this.goToService.goTo('/event/dashboard');},
            error=>{
              if(error.state == 400) this.errorMsg = "Information Incorrect.";
              if(error.state >= 500) this.errorMsg = "Server Not Responding. Please Try Again Later";
              this.invalid = true;
            }
          );
        }
      },
      error=>{
        console.log('error');
        this.invalid = true;
        this.success = false;
        this.errorMsg = "Invalid Location. Please try again";
      }
    );
  }

  constructor(private http:HttpClient, private utilService:UtilService, private goToService:GoToService) {
  }
  ngOnInit() {
  }
}

