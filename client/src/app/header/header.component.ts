import { Component } from '@angular/core';
import { ModalDialogService } from 'src/app/services/modal-dialog.service';

@Component({
	selector: 'header-chatagent',
	templateUrl: './header.component.html'
})
export class HeaderComponet {

	constructor(private modalDialog: ModalDialogService) {
	}

	onGlobal() {
		this.modalDialog.toogleGlobalMessage();
	}

	onPrivate() {
		this.modalDialog.tooglePrivateMessage();
	}
}