import { Component } from '@angular/core';
import { ModalDialogService } from 'src/app/services/modal-dialog.service';
import { CurrentUserService } from 'src/app/services/current-user.service';
import { RESTMessage } from 'src/app/services/rest-messages.service';
import { NgForm } from '@angular/forms';
import { MessageModel } from 'src/app/model/message.model';

@Component({
	selector: 'app-private-message',
	templateUrl: './private-message.component.html',
	styleUrls: ['./private-message.component.css']
})
export class PrivateMessageComponent {

	constructor(private modalDialog: ModalDialogService, private rest: RESTMessage, private user: CurrentUserService) { }

	onClose() {
		this.modalDialog.tooglePrivateMessage("no one");
	}

	onPost(form: NgForm) {

		let header = form.value.inputHeader;
		let subject = form.value.inputSubject;

		if (this.validateForm(header, subject)) {
			return;
		}

		let message: MessageModel = new MessageModel(this.modalDialog.getReciverName(), this.user.username, new Date(), header, subject);
		this.rest.sentPrivateMessage(message).subscribe();
		this.onClose();
	}

	private validateForm(header: string, subject: string): boolean {
		if (header != null && subject != null && header.trim() !== "" && subject.trim() !== "") {
			return false;
		} else return true;
	}

}
