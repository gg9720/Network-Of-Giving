import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RegistrationComponent } from './registration/registration.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { CharityComponent } from './charity/charity.component';
import { AuthGuard } from './helpers/auth.guard';
import { ProfileComponent } from './profile/profile.component';
import { CreateComponent } from './create/create.component';


const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  {
    path:'home', component: HomeComponent
  },
  {
    path: 'signup', component: RegistrationComponent
  },
  {
    path: 'login', component: LoginComponent
  },
  {
    path: 'charity/:id', component: CharityComponent
  },
  {
    path: 'create', component: CreateComponent, canActivate: [AuthGuard]
  },
  {
    path: 'profile', component: ProfileComponent, canActivate: [AuthGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
