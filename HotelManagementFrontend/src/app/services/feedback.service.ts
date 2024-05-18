import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {FeedbackRequest} from "../models/FeedbackRequest";
import {Observable} from "rxjs";
import {Feedback} from "../models/Feedback";

@Injectable({
  providedIn: 'root'
})
export class FeedbackService {
  readonly baseURL = "http://localhost:8888/feedbacks";

  constructor(private httpClient: HttpClient) {
  }

  public add(feedback: FeedbackRequest): Observable<Feedback> {
    const headers = {'Content-Type': 'application/json'};
    return this.httpClient.post<Feedback>(this.baseURL, feedback, {headers})
  }
}
