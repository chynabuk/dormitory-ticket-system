import { HttpClient } from '@angular/common/http';
import { inject } from '@angular/core';
import { ActivatedRouteSnapshot, CanMatchFn, ResolveFn, Router, Routes } from '@angular/router';
import { api, user } from './const-variables';
import { DashboardComponent } from './dashboard/dashboard.component';
import { KanbanComponent } from './dashboard/kanban/kanban.component';
import { NewTicketComponent } from './dashboard/new-ticket/new-ticket.component';
import { TicketComponent } from './dashboard/ticket/ticket.component';
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

export const signOutProcess: ResolveFn<void> = (
    route: ActivatedRouteSnapshot
) => {
    const loginFormService = inject(LoginFormService);
    loginFormService.removeProfile();
};

export const routes: Routes = [
    {
        path: '',
        component: DashboardComponent,
        runGuardsAndResolvers: 'always',
        children: [
            { path: 'kanban', component: KanbanComponent },
            { path: 'tickets', component: TicketsTableComponent },
            { path: 'users', component: UsersTableComponent },
            { path: 'ticket', component: TicketComponent },
            { path: 'new-ticket', component: NewTicketComponent }
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
        resolve: [
            signOutProcess
        ],
        component: LoginFormComponent
    }
];
