import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Hotel} from "../models/Hotel";

@Injectable({
    providedIn: 'root'
})
export class HotelService {
    readonly baseURL = "http://localhost:8888/hotels";

    constructor(private httpClient: HttpClient) {
    }

    public getAllInSpecifiedRadius(radius: number, latitude: number, longitude: number): Observable<Hotel[]> {
        const headers = {'Content-Type': 'application/json'};
        return this.httpClient.get<Hotel[]>(this.baseURL + "/" + radius + '/' + latitude + '/' + longitude, {headers});
    }
}
