import { Component, OnInit } from '@angular/core';
import {TokenStorageService} from '../_services/token-storage.service';
import {AuthService} from '../_services/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  private roles: string[];
  isLoggedIn = false;
  showSYSADMIN = false;
  showADMIN = false;
  showUSER = false;
  showDerma = false;
  showPharma = false;
  showSupplier = false;
  username: string;
  role: string;

  constructor(private tokenStorageService: TokenStorageService) { }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;
      this.username = this.tokenStorageService.getUsername();
    }
  }

  logout(): void {
    this.tokenStorageService.signOut();
    window.location.reload();
  }

}
