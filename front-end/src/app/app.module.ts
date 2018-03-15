import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { RegisterComponent } from './register/register.component';
import { AppRoutingModule } from './/app-routing.module';
import { AuthenticationService } from './_services/authentication.service';
import {RouterModule, Routes} from '@angular/router';
import {AuthGuard} from './_guards/auth.guard';
import { HomeComponent } from './home/home.component';

const appRoutes: Routes = [
  {
    path: '',
    // change this later
    component: HomeComponent
  },
  {
    path: '',
    canActivate: [AuthGuard],
    children: [
      {
        path: 'register',
        component: RegisterComponent
      }
    ]
  }
];

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HeaderComponent,
    FooterComponent,
    RegisterComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(appRoutes),
    AppRoutingModule
  ],
  providers: [AuthenticationService],
  bootstrap: [AppComponent]
})
export class AppModule { }
