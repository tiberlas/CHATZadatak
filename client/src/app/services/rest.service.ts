import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { UserModel } from '../model/user.model';

@Injectable({ providedIn: 'root' })
export class RESTService {

    private BASE_URL: string = 'http://localhost:8080/ChatWAR/chat-rest';

    constructor(private http: HttpClient) { }

    login(user: UserModel): Observable<any> {
        return this.http.post(
            this.BASE_URL + '/users/login',
            user);
    }

    registerUser(user: UserModel): Observable<any> {
        return this.http.post(
            this.BASE_URL + '/users/register',
            user
        );
    }

    getAllRegisteredUsers(user: UserModel): Observable<any> {
        return this.http.get<UserModel[]>(
            this.BASE_URL + '/users/registered'
        );
    }
}