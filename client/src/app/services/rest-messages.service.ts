import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { MessageModel } from '../model/message.model';
import { Observable } from 'rxjs';
import { CurrentUserService } from './current-user.service';
import { map } from 'rxjs/operators';

@Injectable({ providedIn: 'root' })
export class RESTMessage {

	private BASE_URL: string = 'http://localhost:8080/ChatWAR/chat-rest/messages';

	constructor(private http: HttpClient, private client: CurrentUserService) { }

	sentPublicMessage(message: MessageModel): Observable<any> {
		return this.http.post(this.BASE_URL + '/all', message);
	}

	sentPrivateMessage(message: MessageModel): Observable<any> {
		return this.http.post(this.BASE_URL + '/user', message);
	}

	getAllMessages(): Observable<any> {
		return this.http.get<MessageModel[]>(this.BASE_URL + '/' + this.client.username)
			.pipe(
				map(response => {
					const messageArray: MessageModel[] = [];

					for (let i = 0; i < response.length; ++i) {
						messageArray.push({
							resiver: response[i].resiver,
							sender: response[i].sender,
							creationDate: new Date(response[i].creationDate),
							header: response[i].header,
							subject: response[i].subject
						});
					}

					return messageArray;
				})
			);
	}

}