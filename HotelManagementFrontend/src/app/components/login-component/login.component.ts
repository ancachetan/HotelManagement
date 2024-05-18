import {Component} from '@angular/core';
import {HttpClientModule} from "@angular/common/http";
import {UserService} from "../../services/user.service";
import {FormBuilder, FormGroup, ReactiveFormsModule} from "@angular/forms";
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {NgIf} from "@angular/common";

@Component({
  selector: 'app-login-component',
  standalone: true,
  imports: [HttpClientModule, ReactiveFormsModule, NgIf],
  providers: [UserService],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  protected loginForm: FormGroup;
  protected errorLogin: string | undefined;

  constructor(
    protected activeModal: NgbActiveModal,
    private formBuilder: FormBuilder,
    private userService: UserService
  ) {
    this.loginForm = this.formBuilder.group({
      username: [''],
      password: ['']
    });
  }

  protected login() {
    if (this.loginForm.valid) {
      const {username, password} = this.loginForm.value;
      this.userService.login(username, password).subscribe({
        next: user => {
          sessionStorage.setItem('userId', user.id!.toString());
          alert('Login successfully!')
          this.activeModal.dismiss()
        },
        error: () => {
          this.errorLogin = 'Invalid username or password!';
        }
      })
    }
  }
}
