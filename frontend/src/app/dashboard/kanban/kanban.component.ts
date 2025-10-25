import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import $ from 'jquery';
import { api, issues } from '../../const-variables';
import { LoginFormService } from '../../login-form/login-form.service';
import Swal from 'sweetalert2';
import { dateTimeFormatter } from '../../functions';

@Component({
    selector: 'app-kanban',
    standalone: true,
    imports: [],
    templateUrl: './kanban.component.html',
    styleUrl: './kanban.component.css'
})
export class KanbanComponent {
    public kanbanItems:any;
    public dateTimeFormatter = dateTimeFormatter;
    public api = api;

    constructor(private httpClient: HttpClient, private loginFormService: LoginFormService) {}


    ngAfterViewChecked() {
        $(".kanban-card").on("dragstart", (e: any) => {
            e.originalEvent.dataTransfer.setData('kanban-card', $(e.target).attr('id'));
        });
    }

    ngOnInit() { 
        this.httpClient.get(api + issues + "/grouped", 
            { 
                headers: {
                    Authentication: this.loginFormService.userCredentials?.['accessToken']
                }
            }
        ).subscribe((res) => {
            this.kanbanItems = res;
            console.log(res)
        });
    
    $(".kanban-target").on("dragover", (e: any) => {
        e.preventDefault();
    });

    $(".kanban-target").on("drop", (e: any) => {
        e.preventDefault();
        if ($(e.target).hasClass("kanban-card")) {
            $(e.target).after($('#' + e.originalEvent.dataTransfer.getData('kanban-card')));
        } else {
            $(e.currentTarget).append($('#' + e.originalEvent.dataTransfer.getData('kanban-card')));
        }
        this.httpClient.put(api + issues + "/update/status", 
            {
                id: e.originalEvent.dataTransfer.getData('kanban-card').replace("kanban-card-", ""),
                status: $(e.currentTarget).attr("id")
            },
            { 
                headers: {
                    Authentication: this.loginFormService.userCredentials?.['accessToken']
                }
            }
        ).subscribe((res) => {
            Swal.fire({
                toast: true,
                position: 'top-end',
                icon: 'success',
                title: 'Statusänderung',
                text: 'Status erfolgreich geändert!',
                showConfirmButton: false,
                timer: 2000,
                timerProgressBar: true,
                width: 280
            });
        });
    });

   }
}

