import { Component } from '@angular/core';
import { MessageModel } from '../model/message.model';

import { RESTMessage } from '../services/rest-messages.service';
import { MessageService } from '../services/message.service';

@Component({
	selector: 'messages-chatagent',
	templateUrl: './messages.component.html',
	styleUrls: ['./messages.component.css']
})
export class MessagesComponent {

	constructor(private rest: RESTMessage, private messages: MessageService) {
	}

	ngOnInit() {
		this.getAllMessages();
	}

	onRefresh() {
		this.getAllMessages();
	}

	private getAllMessages() {
		this.rest.getAllMessages().subscribe(
			(data: MessageModel[]) => {
				this.messages.addMessages(data);
			}
		);
	}

}
