import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { WebSocketService } from './web-socket.service';
import { map } from 'rxjs/operators';

import { UserStatus } from '../model/user-status';

@Injectable({providedIn: 'root'})
export class UserStatusService {

    private WS_URL: string = 'ws://localhost:8080/ChatWAR/users/ws';
    public users: Subject<UserStatus>;

    constructor(private ws: WebSocketService) {
        this.users = <Subject<UserStatus>>ws.connect(this.WS_URL)
            .pipe(
                map(
                    (res: MessageEvent): UserStatus => {
                        let data = JSON.parse(res.data);

                        return {
                            username: data.username,
                            isActive: data.isActive
                        }
                    }
                )
            );

    }
}