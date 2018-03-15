import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
// import { RegisterComponent } from "./register/register.component";
import { AuthGuard} from './_guards/auth.guard';

const appRoutes: Routes = [
  // { path: '', component: LandingComponent, canActivate: [AuthGuard] },
  { path: 'login', component: LoginComponent },
  // { path: 'register', component: RegisterComponent },

  // otherwise redirect to home
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: []
})
export class AppRoutingModule { }
