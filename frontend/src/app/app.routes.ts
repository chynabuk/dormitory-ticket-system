import { Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { LoginFormComponent } from './login-form/login-form.component';

export const routes: Routes = [
    {
        path: '',
        component: DashboardComponent
    },
    {
        path: 'sign-out',
        redirectTo: '/sign-in'
    },
    {
        path: 'sign-in',
        component: LoginFormComponent
    }
];
