import { HttpClient } from '@angular/common/http';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import html2pdf from 'html2pdf.js';
import { Subscription } from 'rxjs';
import { api, issues } from '../../const-variables';
import { dateTimeFormatter } from '../../functions';
import { LoginFormService } from '../../login-form/login-form.service';

@Component({
  selector: 'app-ticket',
  imports: [],
  standalone: true,
  templateUrl: './ticket.component.html',
  styleUrl: './ticket.component.css'
})
export class TicketComponent implements OnInit, OnDestroy {
  private subscription?: Subscription;
  public ticket?: any;
  public dateTimeFormatter = dateTimeFormatter;

  constructor(private route: ActivatedRoute, private httpClient: HttpClient, private loginFormService: LoginFormService) { }

  public ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    const accessToken = this.loginFormService.userCredentials?.['accessToken'];

    this.subscription = this.httpClient.get(api + issues + `/${id}`, {
      headers: {
        Authorization: accessToken
      }
    }).subscribe({
      next: (res) => this.ticket = res
    })
  }

  public ngOnDestroy(): void {

  }

  public htmlToPDF() {
    let element = document.getElementById('paperToPdf');
    if (element) {
      element.classList.add("border-bottom")
      element.classList.add("border-3")
      const options = {
        filename: 'ticket.pdf',
        jsPDF: {
          unit: 'mm',
          format: 'a4',
          orientation: 'portrait' as 'portrait',
        },
      };

      html2pdf().set(options).from(element).save();
    }
  }
}
