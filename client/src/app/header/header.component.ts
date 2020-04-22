import { Component } from '@angular/core';
import { ModalDialogService } from 'src/app/services/modal-dialog.service';
import { RESTLogin } from '../services/rest-login.service';
import { Router } from '@angular/router';

@Component({
	selector: 'header-chatagent',
	templateUrl: './header.component.html'
})
export class HeaderComponet {

	constructor(private modalDialog: ModalDialogService,
		private rest: RESTLogin,
		private router: Router) {
	}

	onGlobal() {
		this.modalDialog.toogleGlobalMessage();
	}

	onPrivate() {
		this.modalDialog.tooglePrivateMessage();
	}

	onSignOff() {
		this.rest.logoutUser().subscribe();
		this.router.navigate(['/sign-in']);
	}
}