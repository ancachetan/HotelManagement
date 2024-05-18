import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BookingRequest} from "../models/BookingRequest";
import {Observable} from "rxjs";
import {Booking} from "../models/Booking";
import {BookingWithDetails} from "../models/BookingWithDetails";

@Injectable({
  providedIn: 'root'
})
export class BookingService {
  readonly baseURL = "http://localhost:8888/bookings";

  constructor(private httpClient: HttpClient) {
  }

  public add(bookingRequest: BookingRequest): Observable<Booking> {
    const headers = {'Content-Type': 'application/json'};
    return this.httpClient.post<Booking>(this.baseURL, bookingRequest, {headers});
  }

  public cancel(bookingId: number): Observable<any> {
    const headers = {'Content-Type': 'application/json'};
    return this.httpClient.put<any>(this.baseURL + '/' + bookingId, {}, {headers});
  }

  public updateRoom(bookingId: number, roomId: number): Observable<Booking> {
    const headers = {'Content-Type': 'application/json'};
    return this.httpClient.put<Booking>(this.baseURL + '/' + bookingId + '/' + roomId, {}, {headers});
  }

  public getAllByUserId(userId: number): Observable<BookingWithDetails[]> {
    const headers = {'Content-Type': 'application/json'};
    return this.httpClient.get<BookingWithDetails[]>(this.baseURL + '/' + userId, {headers});
  }
}
