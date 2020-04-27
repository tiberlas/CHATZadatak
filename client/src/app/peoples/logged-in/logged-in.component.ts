import { Component, OnInit } from '@angular/core';
import { RESTLogin } from 'src/app/services/rest-login.service';
import { UserStatus } from 'src/app/model/user-status';
import { UserStatusService } from 'src/app/services/user-status.service';

@Component({
	selector: 'app-logged-in',
	templateUrl: './logged-in.component.html',
	styleUrls: ['./logged-in.component.css']
})
export class LoggedInComponent implements OnInit {

	private users: string[] = [];

	constructor(private rest: RESTLogin, private userStatus: UserStatusService) {
		userStatus.users.subscribe(
			(user: UserStatus) => {
				if(user.isActive === true && this.users.indexOf(user.username) == -1) {
					this.users.push(user.username);
				} else {
					const index = this.users.indexOf(user.username);
					if(index > -1) {
						this.users.splice(index, 1);
					}
				}
			}
		);
	 }

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

	private fakeData() {
		this.users.push("user1");
		this.users.push("user2");
		this.users.push("user3");
		this.users.push("user4");
	}
}
