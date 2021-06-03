import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {UserModel} from '../model/user.model';
import {PromotionModel} from '../model/promotion.model';
import {ToastrService} from 'ngx-toastr';
import {PasswordChangerModel} from '../model/passwordChanger.model';

@Injectable()
export class UserProfileService {
  private path: string;
  public form: any = {};

  constructor(private httpClient: HttpClient,
              private toast: ToastrService
  ) {
  }

  public getUserInfo(username: string): Observable<UserModel> {
    this.path = 'http://localhost:8080/users/username/' + username;
    return this.httpClient.get<UserModel>(this.path);
  }

  public updateProfile(user: UserModel): void {
    this.httpClient.post('http://localhost:8080/users/updateInfo', user).subscribe(
      (response: any) => {
        this.toast.success(`Profil azuriran!`);
      },
      (error => {
        this.toast.error(`Doslo je do greske`);
      })
    );
  }

  public changePassword(pw: PasswordChangerModel): void {
    this.httpClient.post('http://localhost:8080/auth/change-password', pw).subscribe(
      (response: any) => {
        this.toast.success(`Sifra azurirana!`);
      },
      (error => {
        this.toast.error(`Doslo je do greske`);
      })
    );
  }
}
