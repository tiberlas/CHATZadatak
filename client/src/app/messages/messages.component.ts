import { Component, OnInit } from '@angular/core';
import { MessageModel } from '../model/message.model';
import { RESTMessage } from '../services/rest-messages.service';
import { ChatService } from '../services/chat.service';

@Component({
	selector: 'messages-chatagent',
	templateUrl: './messages.component.html',
	styleUrls: ['./messages.component.css']
})
export class MessagesComponent implements OnInit {

	private messages: MessageModel[];

	constructor(private rest: RESTMessage, private chat: ChatService) {
		chat.message.subscribe(
			(msg: MessageModel) => {
				this.messages.push(msg);
			}
		);
	 }

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
