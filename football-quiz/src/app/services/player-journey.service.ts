import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {JourneyTransfer} from "../model/journey-game";

@Injectable({
  providedIn: 'root'
})
export class PlayerJourneyService {
  private readonly API_URL = 'http://localhost:8080/journey-games';

  constructor(private http: HttpClient) { }

  public startJourneyGame(): Observable<JourneyTransfer> {
    return this.http.post<JourneyTransfer>(this.API_URL + '/start', {});
  }

  public makeGuess(gameId: string, playerId: string): Observable<boolean> {
    return this.http.post<boolean>(this.API_URL + '/' + gameId + '/make-guess/' + playerId, {});
  }
}
