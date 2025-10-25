import { Component } from '@angular/core';
import html2pdf from 'html2pdf.js';

@Component({
  selector: 'app-ticket',
  imports: [],
  standalone: true,
  templateUrl: './ticket.component.html',
  styleUrl: './ticket.component.css'
})
export class TicketComponent {
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
