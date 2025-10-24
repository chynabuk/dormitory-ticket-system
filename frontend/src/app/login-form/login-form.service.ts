import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Params, Router } from '@angular/router';
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
    }).subscribe((res) => {
      if (res['error']) {
        alert('Incorrect login or password');
      }
      if (res['accessToken']) {
        this.userCredentials = res;
        localStorage.setItem('profile', JSON.stringify(res));
        this.router.navigate(['/kanban'], { replaceUrl: true });
      }
    })
  }

  public removeProfile() {
    localStorage.removeItem(this.storageKey);
  }
}
