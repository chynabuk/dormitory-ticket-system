import { Component } from '@angular/core';
import { RouterLink } from "@angular/router";
import { LoginFormService } from '../../login-form/login-form.service';

@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.css'
})
export class SidebarComponent {
  public sidebarItems = [
    {
      label: 'Kanban',
      route: 'kanban',
      icon: 'fa-solid fa-clipboard-list',
      role: 'CARETAKER'
    },
    {
      label: 'New Ticket',
      route: 'new-ticket',
      icon: 'fa-solid fa-plus',
      role: 'STUDENT'
    },
    {
      label: 'Tickets',
      route: 'tickets',
      icon: 'fa-solid fa-ticket',
      role: 'ALL'
    }
  ];

  constructor(private loginFormService: LoginFormService) { }

  public get userRole() {
    return this.loginFormService.userCredentials?.['role'];
  }
}
