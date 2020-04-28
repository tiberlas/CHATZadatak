import { Injectable, OnInit } from '@angular/core';
import { Subject } from 'rxjs';
import { MessageModel } from '../model/message.model';
import { WebSocketService } from './web-socket.service';
import { map } from 'rxjs/operators';

@Injectable({ providedIn: 'root' })
export class ChatService {

    private WS_URL: string = 'ws://localhost:8080/ChatWAR/messages/ws';
    public message: Subject<any>;

    constructor(private ws: WebSocketService) {
        this.message = <Subject<any>>ws.connect(this.WS_URL)
            .pipe(
                map(
                    (res: any): any => {
                        console.log('WS MESSAGE FROM BACK');
                        console.log(res);
                        // let data = JSON.parse(res.data);

                        // return {
                        //     reciver: data.reciver,
                        //     sender: data.sender,
                        //     creationDate: data.creationDate,
                        //     header: data.header,
                        //     subject: data.subject
                        // }
                    }
                )
            );
    }

}