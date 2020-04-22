import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { RESTLogin } from '../services/rest-login.service';


@Component({
	selector: 'app-sign-in',
	templateUrl: './sign-in.component.html',
	styleUrls: ['./sign-in.component.css']
})
export class SignInComponent {

	username: string;
	password: string;
	userValid: boolean = true;

	constructor(private router: Router,
		private rest: RESTLogin) { }

	onSignIn(form: NgForm) {
		this.username = form.value.inputUsername;
		this.password = form.value.inputPassword;

		let user = { username: this.username, password: this.password }

		this.userValid = true;
		this.rest.loginUser(user).subscribe(response => {
			console.log(response);
			if (response.status === 200) {
				this.router.navigate(['/home']);
				this.userValid = true;
			} else {
				this.userValid = false;
			}
		}, error => {
			console.log('ERROR 406');
			this.userValid = false;
		});


	}
}
