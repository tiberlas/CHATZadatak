import { Component, Input } from '@angular/core';
import { ModalDialogService } from 'src/app/services/modal-dialog.service';

@Component({
	selector: 'app-people',
	templateUrl: './people.component.html'
})
export class PeopleComponent {

	@Input('name') private name: string;

	constructor(private modalDialog: ModalDialogService) {

	}

	onSelect() {
		this.modalDialog.tooglePrivateMessage(this.name);
	}
}
