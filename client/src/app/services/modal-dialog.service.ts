export class ModalDialogService {

    private modalPrivateMessage: boolean = false;
    private modalGlobalMessage: boolean = false;
    private reciverName: string;

    public toogleGlobalMessage() {
        this.modalGlobalMessage = !this.modalGlobalMessage;
    }

    public tooglePrivateMessage(reciverName: string) {
        this.reciverName = reciverName;
        this.modalPrivateMessage = !this.modalPrivateMessage;
    }

    public getReciverName(): string {
        return this.reciverName;
    }
}