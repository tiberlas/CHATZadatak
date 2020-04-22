export class MessageModel {
    public resiver: string;
    public sender: string;
    public creationDate: Date;
    public header: string;
    public body: string;

    constructor(resiver: string, sender: string, creationDate: Date, header: string, body: string) {
        this.body = body;
        this.creationDate = creationDate;
        this.header = header;
        this.resiver = resiver;
        this.sender = sender;
    }
}