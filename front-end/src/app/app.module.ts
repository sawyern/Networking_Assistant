import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


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
    NewEventComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot([]),
    AppRoutingModule,
    CommonModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [AuthenticationService, AppRoutingModule, GoToService],
  bootstrap: [AppComponent]
})
export class AppModule { }
