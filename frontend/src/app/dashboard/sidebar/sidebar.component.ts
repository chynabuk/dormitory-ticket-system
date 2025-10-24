import { Component } from '@angular/core';
import { RouterLink } from "@angular/router";

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
      icon: 'fa-solid fa-clipboard-list'
    },
    {
      label: 'Tickets',
      route: 'tickets',
      icon: 'fa-solid fa-ticket'
    },
    {
      label: 'Users',
      route: 'users',
      icon: 'fa-solid fa-users'
    }
  ]
}
