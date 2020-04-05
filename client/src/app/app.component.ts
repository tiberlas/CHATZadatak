import { Component, OnInit } from '@angular/core';
import { RESTService } from '../app/services/rest.service';

@Component({
	selector: 'app-root',
	templateUrl: './app.component.html'
})

export class AppComponent implements OnInit {

	constructor(private rest: RESTService) { }

	ngOnInit() {
		this.rest.getLoggedInUssers().subscribe(data => {
			console.log(data);
		});
	}

}
