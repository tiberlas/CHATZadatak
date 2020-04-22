import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserModel } from '../model/user.model';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class RESTLogin {

    private BASE_URL: string = 'http://localhost:8080/ChatWAR/chat-rest/users';
    private username: string;

    constructor(private http: HttpClient) { }

    loginUser(user: UserModel): Observable<any> {
        this.username = user.username;
        return this.http.post(this.BASE_URL + '/login', user, { observe: 'response', responseType: 'text' });
    }

    logoutUser(): Observable<any> {
        return this.http.delete(this.BASE_URL + '/loggedIn/' + this.username, { responseType: 'text' });
    }

    getAllLoggedInUsers(): Observable<any> {
        return this.http.get<string[]>(this.BASE_URL + '/loggedIn');
    }

}