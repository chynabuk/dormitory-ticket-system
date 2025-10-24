import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { LoginFormService } from './login-form.service';

@Component({
  selector: 'app-login-form',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './login-form.component.html',
  styleUrl: './login-form.component.css'
})
export class LoginFormComponent {
  public email = '';
  public password = '';

  constructor(private service: LoginFormService) { }

  public signIn() {
    this.service.signIn(this.email, this.password);
  }
}
