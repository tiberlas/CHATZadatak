import { Component, OnInit } from '@angular/core';
import { RESTLogin } from 'src/app/services/rest-login.service';
import { UserStatus } from 'src/app/model/user-status';
import { LoggedUsersService } from 'src/app/services/logged-users.service';

@Component({
	selector: 'app-logged-in',
	templateUrl: './logged-in.component.html',
	styleUrls: ['./logged-in.component.css']
})
export class LoggedInComponent implements OnInit {

	constructor(private rest: RESTLogin, private users: LoggedUsersService) {

	}

	ngOnInit() {
		this.getAllActiveUsers();
	}

	private getAllActiveUsers() {
		this.rest.getAllLoggedInUsers().subscribe(
			(data: string[]) => {
				this.users.updateActiveUsers(data);
			}
		);
	}

}
