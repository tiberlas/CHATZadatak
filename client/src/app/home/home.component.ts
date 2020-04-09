import { Component, OnInit } from '@angular/core';
import { ModalDialogService } from 'src/app/services/modal-dialog.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private modalDialog: ModalDialogService) { }

  ngOnInit() {
  }

}
