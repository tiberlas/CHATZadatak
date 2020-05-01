import { Component } from '@angular/core';
import { ModalDialogService } from 'src/app/services/modal-dialog.service';
import { NgForm } from '@angular/forms';
import { RESTMessage } from 'src/app/services/rest-messages.service';
import { MessageModel } from 'src/app/model/message.model';
import { CurrentUserService } from 'src/app/services/current-user.service';

@Component({
	selector: 'app-global-message',
	templateUrl: './global-message.component.html',
	styleUrls: ['./global-message.component.css']
})
export class GlobalMessageComponent {

	constructor(private modalDialog: ModalDialogService, private rest: RESTMessage, private user: CurrentUserService) { }

	onClose() {
		this.modalDialog.toogleGlobalMessage();
	}

	onPost(form: NgForm) {

		let header = form.value.inputHeader;
		let subject = form.value.inputSubject;

		if (this.validateForm(header, subject)) {
			return;
		}

		let message: MessageModel = new MessageModel("PUBLIC", this.user.username, new Date(), header, subject);
		this.rest.sentPublicMessage(message).subscribe();
		this.onClose();
	}

	private validateForm(header: string, subject: string): boolean {
		if (header != null && subject != null && header.trim() !== "" && subject.trim() !== "") {
			return false;
		} else return true;
	}

}
