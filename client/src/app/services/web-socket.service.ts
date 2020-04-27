import { Injectable } from '@angular/core';
import { Subject, Observable, Observer } from 'rxjs';
import { CurrentUserService } from './current-user.service';

@Injectable({ providedIn: 'root' })
export class WebSocketService {

    private subject: Subject<MessageEvent>;

    constructor(private currentUser: CurrentUserService) { }

    public connect(url: string) {
        if (!this.subject) {
            this.subject = this.create(url, this.currentUser.username);
        }

        return this.subject;
    }

    private create(url: string, username: string): Subject<MessageEvent> {
        let websc = new WebSocket(url);
        console.log('new: Socket Status: ' + websc.readyState + ' (new)');

        //kad se socket otvori da posalje kom agentu pripada
        //ostatak koda je mapiranje sacketa na observable promenljivu
        websc.onopen = function () {
            console.log('onopen: Socket Status: ' + websc.readyState + ' (open)');
            websc.send(username);
        }

        let observable = Observable.create(
            (obs: Observer<MessageEvent>) => {
                websc.onmessage = obs.next.bind(obs);
                websc.onerror = obs.error.bind(obs);
                websc.onclose = obs.complete.bind(obs);

                return websc.close.bind(websc);
            }
        );

        let observer = {
            next: (data: Object) => {
                if (websc.readyState === WebSocket.OPEN) {
                    websc.send(JSON.stringify(data));
                }
            }
        }

        return Subject.create(observer, observable);
    }
}