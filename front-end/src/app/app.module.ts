import { BrowserModule } from '@angular/platform-browser';
import { NgModule, ApplicationRef } from '@angular/core';


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
import { EventDashboardComponent } from './pages/event-dashboard/event-dashboard/event-dashboard.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { NewEventComponent } from './pages/new-event/new-event/new-event.component';
import {GoToService} from "./_services/go-to/go-to.service";
import {AppRoutingModule} from "./_services/rouuting/app-routing.module";
import {LoginService} from "./_services/authentication/login/login.service";
import {UtilService} from "./_services/util/util.service";
import { LoginPageComponent } from './pages/login/login-page/login-page.component';
import {RegisterService} from "./_services/authentication/register/register.service";
import {LogoutService} from "./_services/authentication/logout/logout.service";
import {GetAccountService} from "./_services/AccountServices/getAccount/get-account.service";
import { EventComponent } from './pages/event-dashboard/event/event.component';
import { EventlistComponent } from './pages/event-dashboard/eventlist/eventlist.component';
import { AgmCoreModule } from '@agm/core';
import {EventFormComponent} from "./pages/new-event/event-form/event-form.component";
import { MyEventsComponent } from './pages/event-dashboard/my-events/my-events.component';
import {UserInfoComponent} from "./pages/profile/user-info/user-info.component";
import {StarComponent} from "./pages/profile/star/star.component";
import {PutAccountService} from "./_services/AccountServices/putAccount/put-account.service";
import {StarServiceService} from "./_services/AccountServices/star-service.service";

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
    NewEventComponent,
    EventComponent,
    EventlistComponent,
    EventFormComponent,
    MyEventsComponent
    EventFormComponent,
    UserInfoComponent,
    StarComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot([]),
    AppRoutingModule,
    CommonModule,
    FormsModule,
    HttpClientModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyBsUeBPaFr-gmdDk-LmZE-nb67aC-5x1Qs'
    })
  ],
  providers: [
    GoToService,
    LoginService,
    UtilService,
    RegisterService,
    LogoutService,
    GetAccountService,
    PutAccountService,
    StarServiceService,
    AuthGuard
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

