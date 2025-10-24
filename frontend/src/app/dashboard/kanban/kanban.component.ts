import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import $ from 'jquery';
import { api, issues } from '../../const-variables';
import { LoginFormService } from '../../login-form/login-form.service';

@Component({
    selector: 'app-kanban',
    standalone: true,
    imports: [],
    templateUrl: './kanban.component.html',
    styleUrl: './kanban.component.css'
})
export class KanbanComponent {

    constructor(private httpClient: HttpClient, private loginFormService: LoginFormService) {}

    ngOnInit() { 
        this.httpClient.get(api + issues, 
            { 
                headers: {
                    Authentication: this.loginFormService.userCredentials?.['accessToken']
                }
            }
        ).subscribe((res) => {
            console.log(res);
        });
    $(".kanban-card").on("dragstart", (e: any) => {
        e.originalEvent.dataTransfer.setData('kanban-card', $(e.target).attr('id'));
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
    });

   }
}

