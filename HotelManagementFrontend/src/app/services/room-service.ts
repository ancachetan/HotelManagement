import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Room} from "../models/Room";

@Injectable({
    providedIn: 'root'
})
export class RoomService {
    readonly baseURL = "http://localhost:8888/rooms";

    constructor(private httpClient: HttpClient) {
    }

    public getAllAvailableInSpecifiedInterval(hotelId: number, checkInDate: string, checkOutDate: string): Observable<Room[]> {
        const headers = {'Content-Type': 'application/json'};
        return this.httpClient.get<Room[]>(this.baseURL + "/" + hotelId + '/' + checkInDate + '/' + checkOutDate, {headers});
    }
}
