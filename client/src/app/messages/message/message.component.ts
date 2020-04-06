import { Component, OnInit, Input } from '@angular/core';
import { MessageModel } from 'src/app/model/message.model';

@Component({
  selector: 'app-message',
  templateUrl: './message.component.html',
  styleUrls: ['./message.component.css']
})
export class MessageComponent implements OnInit {

  @Input('message') message: MessageModel;

  constructor() { }

  ngOnInit() {
  }

}
