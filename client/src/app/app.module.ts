import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { HeaderComponet } from './header/header.component';
import { PeoplesComponent } from './peoples/peoples.component';
import { RegisteredComponent } from './peoples/registered/registered.component';
import { LoggedInComponent } from './peoples/logged-in/logged-in.component';
import { MessagesComponent } from './messages/messages.component';
import { MessageComponent } from './messages/message/message.component';
import { PrivateMessageComponent } from './modal/private-message/private-message.component';
import { GlobalMessageComponent } from './modal/global-message/global-message.component';
import { SignInComponent } from './sign-in/sign-in.component';
import { RegisterComponent } from './sign-in/register/register.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { HomeComponent } from './home/home.component';
import { PeopleComponent } from './peoples/people/people.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponet,
    PeoplesComponent,
    RegisteredComponent,
    LoggedInComponent,
    MessagesComponent,
    MessageComponent,
    PrivateMessageComponent,
    GlobalMessageComponent,
    SignInComponent,
    RegisterComponent,
    NotFoundComponent,
    HomeComponent,
    PeopleComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
