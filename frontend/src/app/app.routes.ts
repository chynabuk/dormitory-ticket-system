import { Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { KanbanComponent } from './dashboard/kanban/kanban.component';
import { TicketsTableComponent } from './dashboard/tickets-table/tickets-table.component';
import { UsersTableComponent } from './dashboard/users-table/users-table.component';
import { LoginFormComponent } from './login-form/login-form.component';

export const routes: Routes = [
    {
        path: '',
        component: DashboardComponent,
        children: [
            { path: 'kanban', component: KanbanComponent },
            { path: 'tickets', component: TicketsTableComponent },
            { path: 'users', component: UsersTableComponent }
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
