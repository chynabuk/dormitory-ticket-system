import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Params, Router } from '@angular/router';
import Swal from 'sweetalert2';
import { api, user } from '../const-variables';

@Injectable({
  providedIn: 'root'
})
export class LoginFormService {
  private storageKey = 'profile';
  public userCredentials?: Params = JSON.parse(localStorage.getItem(this.storageKey) || '{}') || {};
  constructor(private httpClient: HttpClient, private router: Router) { }

  public signIn(email: string, password: string) {
    this.httpClient.post<Params>(api + user + '/login', {
      email,
      password
    }).subscribe({
      next: (res) => {
        if (res['accessToken']) {
          this.userCredentials = res;
          this.userCredentials['accessToken'] = 'Bearer ' + this.userCredentials['accessToken'];
          localStorage.setItem('profile', JSON.stringify(res));
          this.router.navigate(['/kanban'], { replaceUrl: true });
        }
      },
      error: (err) => {
        Swal.fire({
          icon: "error",
          title: "Fehler",
          text: "Sie haben einen falschen Benutzernamen oder ein falsches Passwort eingegeben!"
        });
      }
    });
  }

  public removeProfile() {
    localStorage.removeItem(this.storageKey);
  }
}
