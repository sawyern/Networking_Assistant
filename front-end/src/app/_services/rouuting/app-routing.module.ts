import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard} from '../../_guards/auth.guard';
import {HomeComponent} from '../../pages/home/home.component';
import {ProfileComponent} from "../../pages/profile/profile.component";
import {EventDashboardComponent} from "../../pages/event-dashboard/event-dashboard/event-dashboard.component";
import {NewEventComponent} from "../../pages/new-event/new-event/new-event.component";
import {LoginPageComponent} from "../../pages/login/login-page/login-page.component";
import {StarComponent} from "../../pages/profile/star/star.component";
import {MyEventsComponent} from "../../pages/event-dashboard/my-events/my-events.component";
import {StarredComponent} from "../../pages/profile/starred/starred.component";

const appRoutes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginPageComponent },
  { path: 'profile/:accountId', component: ProfileComponent },
  { path: 'event/dashboard', component: EventDashboardComponent },
  { path: '',
    canActivate: [AuthGuard],
    children:[
      { path: 'profile/:accountId/starred', component: StarComponent },
      { path: 'event/new', component: NewEventComponent },
      { path: 'account/starred', component: StarredComponent},
      { path: 'event/my-events', component: MyEventsComponent}
    ]
  },
  // otherwise redirect to home
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(appRoutes)
  ],
  declarations: []
})
export class AppRoutingModule { }
