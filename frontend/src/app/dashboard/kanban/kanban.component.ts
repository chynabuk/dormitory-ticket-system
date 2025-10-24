import { Component } from '@angular/core';
import $ from 'jquery';

@Component({
  selector: 'app-kanban',
  standalone: true,
  imports: [],
  templateUrl: './kanban.component.html',
  styleUrl: './kanban.component.css'
})
export class KanbanComponent {
  constructor() {} 
   ngOnInit() { 
      
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

