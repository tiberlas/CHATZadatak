import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { UserModel } from '../model/user.model';

@Injectable({ providedIn: 'root' })
export class RESTRegister {

    private BASE_URL: string = 'http://localhost:8080/ChatWAR/chat-rest/users';

    constructor(private http: HttpClient) { }

    registerUser(user: UserModel): Observable<any> {
        return this.http.post(this.BASE_URL + '/register', user, { observe: 'response', responseType: 'text' });
    }

    getAllRegisteredUsers(): Observable<any> {
        return this.http.get<string[]>(this.BASE_URL + '/registered');
    }
}