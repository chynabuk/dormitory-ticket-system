import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import DataTable from 'datatables.net';
import 'datatables.net-buttons-dt';
import 'datatables.net-buttons/js/buttons.colVis.mjs';
import 'datatables.net-buttons/js/buttons.html5.mjs';
import 'datatables.net-buttons/js/buttons.print.mjs';
import 'datatables.net-buttons/js/dataTables.buttons.mjs';
import { api, issues, status } from '../../const-variables';
import { dateTimeFormatter } from '../../functions';
import { LoginFormService } from '../../login-form/login-form.service';

@Component({
  selector: 'app-tickets-table',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './tickets-table.component.html',
  styleUrl: './tickets-table.component.css'
})
export class TicketsTableComponent {
  public ticketsTable?: any;
  public tableItems: any;
  public dateTimeFormatter = dateTimeFormatter;

  constructor(private httpClient: HttpClient, private loginFormService: LoginFormService) { }

  ngOnInit() {
    const userRole = this.loginFormService.userCredentials?.['role'];
    const accessToken = this.loginFormService.userCredentials?.['accessToken'];
    const id = this.loginFormService.userCredentials?.['id'];
    this.httpClient.get(api + issues + (userRole === 'STUDENT' ? `/user/${id}` : ''), {
      headers: {
        Authentication: accessToken || ''
      }
    }).subscribe((res) => {
      this.tableItems = res;
      this.ticketsTable = new DataTable('#tickets', {
        data: this.tableItems,
        columns: [
          { data: 'id', title: 'ID' },
          { data: 'createdByName', title: 'Mieter' },
          { data: 'apartmentNumber', title: 'Wohnung' },
          { data: 'roomNumber', title: 'Raum' },
          { data: 'priority', title: 'Priorität' },
          { data: 'currentStatus', title: 'Status', render: (data: string) => status[data] },
          { data: 'createdDateTime', title: 'Erstellt um', render: (data: string) => dateTimeFormatter(data) },
          { data: 'id', title: 'Aktion', render: (data: string) => `<div class="w-100 d-flex justify-content-center"><a href="ticket/${data}"><i class="fa-solid fa-arrow-right cursor-pointer"></i></a></div>` },
        ],
        layout: {
          topStart: {
            buttons: ['pageLength', 'print']
          }
        },
        "pageLength": 25,
        language: {
          decimal: ",",
          thousands: ".",
          emptyTable: "Keine Daten in der Tabelle vorhanden",
          info: "_START_ bis _END_ von _TOTAL_ Einträgen",
          infoEmpty: "Keine Einträge vorhanden",
          infoFiltered: "(gefiltert aus _MAX_ gesamten Einträgen)",
          infoPostFix: "",
          lengthMenu: "_MENU_ Einträge anzeigen",
          loadingRecords: "Lade...",
          processing: "Bitte warten...",
          search: "Suche:",
          zeroRecords: "Keine passenden Einträge gefunden",
          paginate: {
            first: "Erste",
            last: "Letzte",
            next: "Nächste",
            previous: "Vorherige"
          },
          buttons: {
            print: "Drucken",
            pageLength: "Zeilen pro Seite"
          }
        }
      });
    });
  }
}
