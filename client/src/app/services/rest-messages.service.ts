import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { MessageModel } from '../model/message.model';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class RESTMessage {

    private BASE_URL: string = 'http://localhost:8080/ChatWAR/chat-rest/messages';

    constructor(private http: HttpClient) { }

    sentPublicMessage(message: MessageModel): Observable<any> {
        return this.http.post(this.BASE_URL + '/all', message);
    }

    sentPrivateMessage(message: MessageModel): Observable<any> {
        return this.http.post(this.BASE_URL + '/user', message);
    }

    getAllMessages(username: string): Observable<any> {
        return this.http.get<MessageModel[]>(this.BASE_URL + '/' + username);
    }

}