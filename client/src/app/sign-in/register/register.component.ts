import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { RESTService } from 'src/app/services/rest.service';

import { UserModel } from '../../model/user.model';

@Component({
	selector: 'app-register',
	templateUrl: './register.component.html',
	styleUrls: ['./register.component.css']
})
export class RegisterComponent {

	private newUser: UserModel;
	private usernameTaken: boolean = false;
	private passwordsDontMatch: boolean = false;

	constructor(private rest: RESTService) { }

	onCreateAccount(form: NgForm) {
		if (form.value.inputPassword === form.value.inputPassword2) {
			this.passwordsDontMatch = false;
		} else {
			this.passwordsDontMatch = true;
			return;
		}

		this.newUser = new UserModel(form.value.inputUsername,
			form.value.inputPassword);
		this.usernameTaken = false;

		this.rest.registerUser(this.newUser).subscribe(data => {
			console.log('new user is registred');
		});


	}
}
