import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
// import { RegisterComponent } from "./register/register.component";
import { AuthGuard} from './_guards/auth.guard';
import {HomeComponent} from './home/home.component';
import {RegisterComponent} from "./register/register.component";
import {ProfileComponent} from "./profile/profile.component";
import {EventDashboardComponent} from "./event-dashboard/event-dashboard.component";
import {NewEventComponent} from "./new-event/new-event.component";

const appRoutes: Routes = [
  { path: '', component: HomeComponent },
  // { path: '', component: LandingComponent, canActivate: [AuthGuard] },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'event/dashboard', component: EventDashboardComponent },
  { path: 'event/new', component: NewEventComponent },

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
