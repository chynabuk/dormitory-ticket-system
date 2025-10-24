import { Component } from '@angular/core';
import $ from 'jquery';
import DataTable from 'datatables.net';
import 'datatables.net-buttons-dt';
import 'datatables.net-buttons/js/dataTables.buttons.mjs';
import 'datatables.net-buttons/js/buttons.html5.mjs';
import 'datatables.net-buttons/js/buttons.print.mjs';
import 'datatables.net-buttons/js/buttons.colVis.mjs';

@Component({
  selector: 'app-tickets-table',
  standalone: true,
  imports: [],
  templateUrl: './tickets-table.component.html',
  styleUrl: './tickets-table.component.css'
})
export class TicketsTableComponent {
  public ticketsTable?:any;

  ngOnInit() { 
    this.ticketsTable = new DataTable('#tickets', {
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
  }
}
