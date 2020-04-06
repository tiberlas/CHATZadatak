import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
	selector: 'app-sign-in',
	templateUrl: './sign-in.component.html',
	styleUrls: ['./sign-in.component.css']
})
export class SignInComponent {

	username: string;
	password: string;
	userValid: boolean = true;

	constructor(private router: Router) { }

	onSignIn(form: NgForm) {
		this.username = form.value.inputUsername;
		this.password = form.value.inputPassword;

		//ovde ide http zahtev za logovanje
		//kredencijali su mokovani
		this.userValid = true;
		if (this.username === 'tibi' && this.password === '1234') {
			this.router.navigate(['/home']);
		} else {
			this.userValid = false;
		}
	}
}
