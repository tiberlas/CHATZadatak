import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class RESTService {

    constructor(private http: HttpClient) { }

    getLoggedInUssers(): Observable<string> {
        console.log("HTTP GET")
        return this.http.get<string>('http://localhost:8080/RESTApp/rest/demo/test');
    }

}