import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Transfer} from "../model/transfer";
import {SearchResult} from "../model/search";

@Injectable({
  providedIn: 'root'
})
export class PlayerSearchService {
  private readonly API_URL = 'http://localhost:8080/transfermarkt-search';

  constructor(private http: HttpClient) { }

  searchPlayers(searchText: string = '', page: number = 1): Observable<SearchResult> {
    return this.http.post<SearchResult>(this.API_URL, {page: page, searchText: searchText});
  }
}
