import { Component, OnInit } from '@angular/core';

@Component({
	selector: 'app-registered',
	templateUrl: './registered.component.html',
	styleUrls: ['./registered.component.css']
})
export class RegisteredComponent implements OnInit {

	private users: string[];

	constructor() { }

	ngOnInit() {
		this.users = [
			'tibi',
			'kiki',
			'Magnus Veliki Magnuson drugi'
		];
	}

}
