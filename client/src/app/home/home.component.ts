import { Component, OnInit, OnDestroy } from '@angular/core';
import { webSocket } from 'rxjs/webSocket';

import { ModalDialogService } from 'src/app/services/modal-dialog.service';
import { CurrentUserService } from '../services/current-user.service';
import { MessageModel } from '../model/message.model';
import { UserStatus } from '../model/user-status';
import { LoggedUsersService } from '../services/logged-users.service';
import { MessageService } from '../services/message.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit, OnDestroy {

  private ws;

  constructor(private modalDialog: ModalDialogService,
    private curentUser: CurrentUserService,
    private loggedInUsers: LoggedUsersService,
    private messages: MessageService) { }

  ngOnInit() {
    this.ws = webSocket('ws://localhost:8080/ChatWAR/ws');
    console.log('WEB SOCKET STARTED');
    this.ws.subscribe(
      (msg: any) => {
        if (msg.username == null) {
          let message: MessageModel = {
            sender: msg.sender,
            reciver: msg.reciver,
            subject: msg.subject,
            header: msg.header,
            creationDate: new Date(msg.creationDate)
          }
          console.log(message);
          this.messages.addMessage(message);

        } else {
          let userstatus: UserStatus = {
            isActive: msg.isActive,
            username: msg.username
          }
          console.log(userstatus);
          this.loggedInUsers.userstatusChanged(userstatus);
        }
      },
      (err) => console.log(err),
      () => console.log('complete')
    );

    this.ws.next(this.curentUser.username);
  }

  ngOnDestroy() {
    this.ws.unsubscribe();
  }

}
