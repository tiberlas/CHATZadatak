import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { RESTRegister } from 'src/app/services/rest-register.service';

import { UserModel } from '../../model/user.model';
import { from } from 'rxjs';
import { Router } from '@angular/router';

@Component({
	selector: 'app-register',
	templateUrl: './register.component.html',
	styleUrls: ['./register.component.css']
})
export class RegisterComponent {

	private newUser: UserModel;
	private usernameTaken: boolean = false;
	private passwordsDontMatch: boolean = false;

	constructor(private rest: RESTRegister, private router: Router) { }

	onCreateAccount(form: NgForm) {
		if (this.validateInput(form)) {
			this.passwordsDontMatch = false;
		} else {
			this.passwordsDontMatch = true;
			return;
		}

		this.newUser = new UserModel(form.value.inputUsername,
			form.value.inputPassword);
		this.usernameTaken = false;

		this.rest.registerUser(this.newUser).subscribe(response => {
			if (response.status === 201) {
				console.log('CREATED USER');

				alert('Acount successfully created');
				this.router.navigate(['/sign-in']);
			} else {
				this.catchedError();
			}
		}, error => {
			this.catchedError();
		});
	}

	private validateInput(form: NgForm): boolean {
		if (form.value.inputPassword === form.value.inputPassword2 &&
			form.value.inputPassword != null &&
			form.value.inputPassword.trim() !== '' &&
			form.value.inputUsername.trim() !== '' &&
			form.value.inputUsername != null) {

			return true;
		} else {
			return false;
		}
	}

	private catchedError() {
		console.log('ERROR')
		this.usernameTaken = true;
	}
}
