import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from '../../components/login_page/login/login.component';
// import { RegisterComponent } from "./register/register.component";
import { AuthGuard} from '../../_guards/auth.guard';
import {HomeComponent} from '../../components/home/home.component';
import {RegisterComponent} from "../../components/login_page/register/register.component";
import {ProfileComponent} from "../../components/profile/profile.component";
import {EventDashboardComponent} from "../../components/event-dashboard/event-dashboard.component";
import {NewEventComponent} from "../../components/new-event/new-event.component";
import {LoginPageComponent} from "../../components/login_page/login-page/login-page.component";

const appRoutes: Routes = [
  { path: '', component: HomeComponent },
  // { path: '', component: LandingComponent, canActivate: [AuthGuard] },
  { path: 'login', component: LoginPageComponent },
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
