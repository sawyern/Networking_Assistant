import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { LoginComponent } from './pages/login/login/login.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { RegisterComponent } from './pages/login/register/register.component';
import {RouterModule, Routes} from '@angular/router';
import {AuthGuard} from './_guards/auth.guard';
import { HomeComponent } from './pages/home/home.component';
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {CommonModule} from "@angular/common";
import {FormsModule} from "@angular/forms";
import { EventDashboardComponent } from './pages/event-dashboard/event-dashboard.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { NewEventComponent } from './pages/new-event/new-event.component';
import {GoToService} from "./_services/go-to/go-to.service";
import {AppRoutingModule} from "./_services/rouuting/app-routing.module";
import {LoginService} from "./_services/authentication/login/login.service";
import {UtilService} from "./_services/util/util.service";
import { LoginPageComponent } from './pages/login/login-page/login-page.component';
import {RegisterService} from "./_services/authentication/register/register.service";
import {LogoutService} from "./_services/authentication/logout/logout.service";
import {GetAccountService} from "./_services/getAccount/get-account.service";
import { EventListComponent } from './pages/profile/event-list/event-list.component';
import { UserInfoComponent } from './pages/profile/user-info/user-info.component';
import {PutAccountService} from "./_services/putAccount/put-account.service";
import { StarComponent } from './pages/profile/star/star.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HeaderComponent,
    FooterComponent,
    RegisterComponent,
    HomeComponent,
    EventDashboardComponent,
    ProfileComponent,
    NewEventComponent,
    LoginPageComponent,
    EventListComponent,
    UserInfoComponent,
    StarComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot([]),
    AppRoutingModule,
    CommonModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    GoToService,
    LoginService,
    UtilService,
    RegisterService,
    LogoutService,
    GetAccountService,
    PutAccountService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

