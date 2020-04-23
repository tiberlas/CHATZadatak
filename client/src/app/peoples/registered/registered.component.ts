import { Component, OnInit } from '@angular/core';
import { RESTRegister } from 'src/app/services/rest-register.service';

@Component({
	selector: 'app-registered',
	templateUrl: './registered.component.html',
	styleUrls: ['./registered.component.css']
})
export class RegisteredComponent implements OnInit {

	private users: string[];

	constructor(private rest: RESTRegister) { }

	ngOnInit() {
		this.getAllRegisteredUsers();
	}

	private getAllRegisteredUsers() {
		this.rest.getAllRegisteredUsers().subscribe(
			data => {
				this.users = data;
			}
		);
	}

}
