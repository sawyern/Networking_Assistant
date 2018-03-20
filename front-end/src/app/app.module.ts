import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { LoginComponent } from './components/login_page/login/login.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { RegisterComponent } from './components/login_page/register/register.component';
import {RouterModule, Routes} from '@angular/router';
import {AuthGuard} from './_guards/auth.guard';
import { HomeComponent } from './components/home/home.component';
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {CommonModule} from "@angular/common";
import {FormsModule} from "@angular/forms";
import { EventDashboardComponent } from './components/event-dashboard/event-dashboard.component';
import { ProfileComponent } from './components/profile/profile.component';
import { NewEventComponent } from './components/new-event/new-event.component';
import {GoToService} from "./_services/go-to/go-to.service";
import {AppRoutingModule} from "./_services/rouuting/app-routing.module";
import {LoginService} from "./_services/authentication/login/login.service";
import {UtilService} from "./_services/util/util.service";
import { LoginPageComponent } from './components/login_page/login-page/login-page.component';
import {RegisterService} from "./_services/authentication/register/register.service";
import {LogoutService} from "./_services/authentication/logout/logout.service";
import {GetAccountService} from "./_services/getAccount/get-account.service";

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
    LoginPageComponent
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
    GetAccountService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

