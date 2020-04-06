import { Component } from '@angular/core';
import { UserModel } from '../model/user.model';

@Component({
	selector: 'peoples-chatagent',
	templateUrl: './peoples.component.html',
	styleUrls: ['./peoples.component.css']
})
export class PeoplesComponent {

	//if true then only logged users will be shown; else all users are shown
	private showActiveUsers: boolean = true;

	onChangeShowUsers() {
		this.showActiveUsers = !this.showActiveUsers;
	}

}
