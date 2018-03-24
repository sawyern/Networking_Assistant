import {Component, Input, OnInit} from '@angular/core';
import { StarServiceService} from "../../../_services/AccountServices/star-service.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-star',
  templateUrl: './star.component.html',
  styleUrls: ['./star.component.css']
})
export class StarComponent implements OnInit {

  currentUserId = localStorage.getItem('token.accountId');
  @Input() isStarred: boolean;
  isOwnerProfile: boolean;

  constructor(private route: ActivatedRoute,
              private starService: StarServiceService) { }

  ngOnInit() {
    this.getStarred();
    // this.starredId = this.accountId;
  }

  // GET starred info from server
  getStarred(): void {
    console.log('getStarred called');
    const accountId = this.route.snapshot.paramMap.get('accountId');
    this.starService.isStarredById(accountId)
      .toPromise().then( isStarred => {
        this.isOwnerProfile = accountId == this.currentUserId;
        console.log("is starred resp" + isStarred);
        this.isStarred = isStarred.id == this.currentUserId;
        console.log(accountId);
        console.log(this.isStarred);
      });
  }

  toggleStar(): void {
    console.log('Star toggle!');
    const starredId = this.route.snapshot.paramMap.get('accountId');
    if (!this.isStarred) {
      console.log('updated');
      this.starService.updateStarred(starredId).toPromise().then(starredAccount => {
        console.log(starredAccount);
        this.getStarred();
      });
    } else {
      console.log('deleted');
      this.starService.deleteStarredAccount(starredId).toPromise().then(starredAccount => {
        console.log(starredAccount);
        this.getStarred();
      });
    }
  }
}
