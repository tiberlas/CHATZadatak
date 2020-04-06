import { Component, OnInit } from '@angular/core';

@Component({
	selector: 'app-logged-in',
	templateUrl: './logged-in.component.html',
	styleUrls: ['./logged-in.component.css']
})
export class LoggedInComponent implements OnInit {

	private users: string[];

	constructor() { }

	ngOnInit() {
		this.users = [
			'tibi',
			'kiki',
			'sui',
			'ale',
			'Magnus Veliki Magnuson drugi'
		];
	}

}
