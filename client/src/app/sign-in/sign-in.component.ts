import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { RESTService } from '../services/rest.service';

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
		private rest: RESTService) { }

	onSignIn(form: NgForm) {
		this.username = form.value.inputUsername;
		this.password = form.value.inputPassword;

		let user = { username: this.username, password: this.password }

		this.rest.login(user).subscribe(data => {
			console.log('Is logged in', data)
		});

		this.userValid = true;
		if (this.username === 'tibi' && this.password === '1234') {
			this.router.navigate(['/home']);
		} else {
			this.userValid = false;
		}
	}
}
