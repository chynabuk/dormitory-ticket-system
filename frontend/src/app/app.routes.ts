import { HttpClient } from '@angular/common/http';
import { inject } from '@angular/core';
import { CanMatchFn, Router, Routes } from '@angular/router';
import { api, user } from './const-variables';
import { DashboardComponent } from './dashboard/dashboard.component';
import { KanbanComponent } from './dashboard/kanban/kanban.component';
import { TicketsTableComponent } from './dashboard/tickets-table/tickets-table.component';
import { UsersTableComponent } from './dashboard/users-table/users-table.component';
import { LoginFormComponent } from './login-form/login-form.component';
import { LoginFormService } from './login-form/login-form.service';

const userCanHaveAccess: CanMatchFn = () => {
    const router = inject(Router);
    const loginFormService = inject(LoginFormService);
    const httpClient = inject(HttpClient);
    const userCredentials = loginFormService.userCredentials;
    const accessToken = userCredentials?.['accessToken'];

    if (!accessToken) {
        router.navigate(['/sign-out'], { replaceUrl: true });
        return false;
    }

    httpClient.get(api + user + '/authenticated', { headers: { Authentication: accessToken } }).subscribe((res) => {
        return res;
    });
    return false;
};

export const routes: Routes = [
    {
        path: '',
        component: DashboardComponent,
        runGuardsAndResolvers: 'always',
        children: [
            { path: 'kanban', component: KanbanComponent },
            { path: 'tickets', component: TicketsTableComponent },
            { path: 'users', component: UsersTableComponent }
        ],
        resolve: [
            userCanHaveAccess
        ]
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
