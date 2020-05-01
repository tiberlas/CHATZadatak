import { Injectable } from '@angular/core';
import { MessageModel } from '../model/message.model';

@Injectable({ providedIn: 'root' })
export class MessageService {

    private messages: MessageModel[];

    public getAllMessages(): MessageModel[] {
        return this.messages;
    }

    public addMessages(oldMessages: MessageModel[]) {
        this.messages = oldMessages;
    }

    public addMessage(newMessage: MessageModel) {
        this.messages.push(newMessage);
    }
}