export class MessageModel {
    public reciver: string;
    public sender: string;
    public creationDate: Date;
    public header: string;
    public subject: string;

    constructor(reciver: string, sender: string, creationDate: Date, header: string, subject: string) {
        this.subject = subject;
        this.creationDate = creationDate;
        this.header = header;
        this.reciver = reciver;
        this.sender = sender;
    }
}