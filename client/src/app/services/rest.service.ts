import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { UserModel } from '../model/user.model';

@Injectable({ providedIn: 'root' })
export class RESTService {

    private BASE_URL: string = 'http://localhost:8080/ChatWAR/chat-rest';

    constructor(private http: HttpClient) { }

    getLoggedInUssers(): Observable<string> {
        console.log("HTTP GET")
        return this.http.get<string>('http://localhost:8080/RESTApp/rest/demo/test');
    }

    login(user: UserModel): Observable<any> {
        return this.http.post(
            this.BASE_URL + '/users/login',
            user);
    }

}