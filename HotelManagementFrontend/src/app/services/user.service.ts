import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../models/User";

@Injectable({
    providedIn: 'root'
})
export class UserService {
    readonly baseURL = "http://localhost:8888/users";

    constructor(private httpClient: HttpClient) {
    }

    public login(username: string, password: string): Observable<User> {
        const body = JSON.stringify({
                username: username,
                password: password
            }
        )
        const headers = {'Content-Type': 'application/json'};
        return this.httpClient.post<User>(this.baseURL + "/login", body, {headers});
    }

    public register(name: string, username: string, password: string): Observable<User> {
        const body = JSON.stringify({
                name: name,
                username: username,
                password: password
            }
        )
        const headers = {'Content-Type': 'application/json'};
        return this.httpClient.post<User>(this.baseURL + "/register", body, {headers});
    }
}
