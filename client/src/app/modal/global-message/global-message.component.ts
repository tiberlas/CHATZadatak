import { Component } from '@angular/core';
import { ModalDialogService } from 'src/app/services/modal-dialog.service';

@Component({
  selector: 'app-global-message',
  templateUrl: './global-message.component.html',
  styleUrls: ['./global-message.component.css']
})
export class GlobalMessageComponent {

  constructor(private modalDialog: ModalDialogService) {}

  onClose() {
    this.modalDialog.toogleGlobalMessage();
  }

}
