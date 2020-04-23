import { Component, OnInit } from '@angular/core';
import { RESTLogin } from 'src/app/services/rest-login.service';

@Component({
	selector: 'app-logged-in',
	templateUrl: './logged-in.component.html',
	styleUrls: ['./logged-in.component.css']
})
export class LoggedInComponent implements OnInit {

	private users: string[];

	constructor(private rest: RESTLogin) { }

	ngOnInit() {
		this.getAllActiveUsers();
	}

	private getAllActiveUsers() {
		this.rest.getAllLoggedInUsers().subscribe(
			data => {
				this.users = data;
			}
		);
	}
}
