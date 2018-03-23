import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from '../../pages/login/login/login.component';
// import { RegisterComponent } from "./register/register.component";
import { AuthGuard} from '../../_guards/auth.guard';
import {HomeComponent} from '../../pages/home/home.component';
import {RegisterComponent} from "../../pages/login/register/register.component";
import {ProfileComponent} from "../../pages/profile/profile.component";
import {EventDashboardComponent} from "../../pages/event-dashboard/event-dashboard/event-dashboard.component";
import {NewEventComponent} from "../../pages/new-event/new-event/new-event.component";
import {LoginPageComponent} from "../../pages/login/login-page/login-page.component";
import { MyEventsComponent } from '../../pages/event-dashboard/my-events/my-events.component';

const appRoutes: Routes = [
  { path: '', component: HomeComponent },
  // { path: '', component: LandingComponent, canActivate: [AuthGuard] },
  { path: 'login', component: LoginPageComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'event/dashboard', component: EventDashboardComponent },
  { path: 'event/new', component: NewEventComponent },
  { path: 'event/my-events', component: MyEventsComponent },

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
