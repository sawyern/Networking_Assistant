import { Injectable } from '@angular/core';
import { Router } from "@angular/router";

@Injectable()
export class GoToService {

  constructor(private router:Router) { }

  goTo(url:String){
    this.router.navigate([url]);
  }
}
