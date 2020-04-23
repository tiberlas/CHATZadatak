import { Component, OnInit } from '@angular/core';
import { MessageModel } from '../model/message.model';
import { RESTMessage } from '../services/rest-messages.service';

@Component({
	selector: 'messages-chatagent',
	templateUrl: './messages.component.html',
	styleUrls: ['./messages.component.css']
})
export class MessagesComponent implements OnInit {

	private messages: MessageModel[];

	constructor(private rest: RESTMessage) { }

	ngOnInit() {
		this.getAllMessages();
	}

	onRefresh() {
		this.getAllMessages();
	}

	private getAllMessages() {
		this.rest.getAllMessages().subscribe(
			data => {
				this.messages = data;
			}
		);
	}

}
