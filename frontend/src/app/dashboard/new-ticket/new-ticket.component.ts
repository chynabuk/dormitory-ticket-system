import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { api, issues } from '../../const-variables';
import { LoginFormService } from '../../login-form/login-form.service';
import $ from 'jquery';
import { dateTimeFormatter } from '../../functions';
import { FormsModule } from '@angular/forms';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-new-ticket',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './new-ticket.component.html',
  styleUrl: './new-ticket.component.css'
})
export class NewTicketComponent {
  public currentDate:Date = new Date();
  public city:string = "Zwickau";
  public apartmentNumber:string = "";    
  public roomNumber:string = "";     
  public description:string = "";
  public authorizationAccepted:boolean = true;    
  public priority:string = "HIGH";
  public currentStatus:string = "CREATED";    
  public assignedToId:number = 1;
  public createdById:number;
  public name:string;
  public selectedFile?: File;

  constructor(private httpClient: HttpClient, private loginFormService: LoginFormService) {
    this.createdById = this.loginFormService.userCredentials?.['id'];
    this.name = this.loginFormService.userCredentials?.['name'] + " " + this.loginFormService.userCredentials?.['surname'];
  }

  onFileSelected(event: any): void {
    const file = event.target.files[0];
    if (file) {
      this.selectedFile = file;
    }
  }

  public createTicket() {
    const ticketData = {
      currentDate: this.currentDate,
      city: this.city,
      apartmentNumber: this.apartmentNumber,
      roomNumber: this.roomNumber,
      description: this.description,
      authorizationAccepted: this.authorizationAccepted,
      priority: this.priority,
      currentStatus: this.currentStatus,
      assignedToId: this.assignedToId,
      createdById: this.createdById
    };

    const formData = new FormData();
    formData.append('createIssueTicket', new Blob([JSON.stringify(ticketData)], { type: 'application/json' }));

    if (this.selectedFile) {
      formData.append('image', this.selectedFile, this.selectedFile.name);
    }

    this.httpClient.post(api + '/issues', formData, {
      headers: {
        'Authorization': this.loginFormService.userCredentials?.['accessToken']
      }
    })
    .subscribe({
      next: (res) => {
        Swal.fire({
          toast: true,
          position: 'top-end',
          icon: 'success',
          title: 'Ticket erfolgreich erstellt!',
          showConfirmButton: false,
          timer: 2000
        });
      },
      error: (err) => {
        console.error('Fehler beim Upload:', err);
        Swal.fire({
          toast: true,
          position: 'top-end',
          icon: 'error',
          title: 'Upload fehlgeschlagen!',
          showConfirmButton: false,
          timer: 2500
        });
      }
    });
  }
}
