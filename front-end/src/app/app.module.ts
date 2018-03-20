import { BrowserModule } from '@angular/platform-browser';
import { NgModule, ApplicationRef } from '@angular/core';


import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { RegisterComponent } from './components/register/register.component';
import { AppRoutingModule } from './/app-routing.module';
import { AuthenticationService } from './_services/authentication/authentication.service';
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
import { EventComponent } from './event/event.component';
import { EventlistComponent } from './eventlist/eventlist.component';
import { AgmCoreModule } from '@agm/core';

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
    EventComponent,
    EventlistComponent
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
  providers: [AuthenticationService, AppRoutingModule, GoToService, HttpClient],
  bootstrap: [AppComponent]
})
export class AppModule { }
