import { Component, OnInit } from '@angular/core';
import { MessageModel } from '../model/message.model';

@Component({
	selector: 'messages-chatagent',
	templateUrl: './messages.component.html',
	styleUrls: ['./messages.component.css']
})
export class MessagesComponent implements OnInit {

	private messages: MessageModel[];

	constructor() { }

	ngOnInit() {
		this.messages = [
			{
				resiver: 'abc',
				sender: 'asdgfad',
				creationDate: new Date(),
				header: "New Message",
				body: 'Ovo je neka poruka/nnovi red!',
			},
			{
				resiver: 'abc',
				sender: 'asdgfad',
				creationDate: new Date(),
				header: "New",
				body: 'Ovo je neka poruka/nnovi red!dashgaislhgauisgda/niyadgfdasilghlafsd\nyuffoiyiuf',
			},
			{
				resiver: 'abc',
				sender: 'asdgfad',
				creationDate: new Date(),
				header: "New Message3",
				body: 'Ovo je neka poruka/nnovi red!',
			}
		];
	}

	onRefresh() {
		console.log('refresh');
	}

}
