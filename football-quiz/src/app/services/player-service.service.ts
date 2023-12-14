import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Transfer} from "../model/transfer";

@Injectable({
  providedIn: 'root'
})
export class PlayerServiceService {
  private readonly API_URL = 'http://localhost:8080/player/random/transfers';

  constructor(private http: HttpClient,) { }

  getTransfers(): Observable<Transfer[]> {
    return this.http.get<Transfer[]>(this.API_URL);
  }
}
