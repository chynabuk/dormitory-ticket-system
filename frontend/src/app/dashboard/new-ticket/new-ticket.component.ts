import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { api, issues } from '../../const-variables';
import { LoginFormService } from '../../login-form/login-form.service';
import $ from 'jquery';
import { dateTimeFormatter } from '../../functions';

@Component({
  selector: 'app-new-ticket',
  standalone: true,
  imports: [],
  templateUrl: './new-ticket.component.html',
  styleUrl: './new-ticket.component.css'
})
export class NewTicketComponent {
  public currentDate:Date = new Date();
}
