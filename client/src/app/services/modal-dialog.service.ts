export class ModalDialogService {

    modalPrivateMessage: boolean = false;
    modalGlobalMessage: boolean = false;

    toogleGlobalMessage() {
        this.modalGlobalMessage = !this.modalGlobalMessage;
    }

    tooglePrivateMessage() {
        this.modalPrivateMessage = !this.modalPrivateMessage;
    }
}