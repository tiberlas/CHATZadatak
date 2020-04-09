import { Component } from '@angular/core';
import { ModalDialogService } from 'src/app/services/modal-dialog.service';

@Component({
  selector: 'app-private-message',
  templateUrl: './private-message.component.html',
  styleUrls: ['./private-message.component.css']
})
export class PrivateMessageComponent {

  constructor(private modalDialog: ModalDialogService) { }

  onClose() {
    this.modalDialog.tooglePrivateMessage();
  }
}
