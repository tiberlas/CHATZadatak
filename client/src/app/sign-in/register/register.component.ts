import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';

@Component({
	selector: 'app-register',
	templateUrl: './register.component.html',
	styleUrls: ['./register.component.css']
})
export class RegisterComponent {

	private username: string;
	private password: string;
	private usernameTaken: boolean = false;
	private passwordsDontMatch: boolean = false;

	constructor() { }

	onCreateAccount(form: NgForm) {
		if (form.value.inputPassword === form.value.inputPassword2) {
			this.passwordsDontMatch = false;
		} else {
			this.passwordsDontMatch = true;
			return;
		}

		this.username = form.value.inputUsername;
		this.password = form.value.inputPassword;
		this.usernameTaken = false;

		//http zahtev


	}
}
