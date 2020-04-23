export class MessageModel {
    public resiver: string;
    public sender: string;
    public creationDate: Date;
    public header: string;
    public subject: string;

    constructor(resiver: string, sender: string, creationDate: Date, header: string, subject: string) {
        this.subject = subject;
        this.creationDate = creationDate;
        this.header = header;
        this.resiver = resiver;
        this.sender = sender;
    }
}