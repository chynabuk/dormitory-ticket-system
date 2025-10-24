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
      label: 'Tickets',
      route: '',
      icon: 'fa-solid fa-ticket'
    },
    {
      label: 'Users',
      route: '',
      icon: 'fa-solid fa-users'
    }
  ]
}
