import {Component} from '@angular/core';
import {HttpClientModule} from "@angular/common/http";
import {UserService} from "../../services/user.service";
import {NgIf} from "@angular/common";
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {NgbActiveModal, NgbTooltip} from "@ng-bootstrap/ng-bootstrap";

@Component({
    selector: 'app-register-component',
    standalone: true,
    imports: [HttpClientModule, ReactiveFormsModule, NgIf, NgbTooltip],
    providers: [UserService],
    templateUrl: './register.component.html',
    styleUrl: './register.component.css'
})
export class RegisterComponent {
    protected registerForm: FormGroup;
    protected errorRegister: string | undefined;

    constructor(
        protected activeModal: NgbActiveModal,
        private formBuilder: FormBuilder,
        private userService: UserService
    ) {
        this.registerForm = this.formBuilder.group({
            name: ['', [Validators.required, Validators.minLength(5), Validators.pattern('^[a-zA-Z ]{4,20}$')]],
            username: ['', [Validators.required, Validators.pattern('^[A-Za-z0-9+_.-]+@[A-Za-z0-9]{2,5}\\.[A-Za-z]{2,4}$')]],
            password: ['', [Validators.required, Validators.pattern('^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[!@#$%^&*.]).{7,}$')]]
        });
    }

    protected register() {
        if (this.registerForm.valid) {
            const {name, username, password} = this.registerForm.value;
            this.userService.register(name, username, password).subscribe({
                next: user => {
                    sessionStorage.setItem('userId', user.id!.toString());
                    alert('Registration successful!')
                    this.activeModal.dismiss()
                },
                error: () => {
                    this.errorRegister = 'There is already an account with this email!';
                }
            })
        } else {
            this.errorRegister = 'Wrong details, please check specifications!'
        }
    }
}
