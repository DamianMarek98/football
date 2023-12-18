import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class PlayerJourneyService {
  private readonly API_URL = 'http://localhost:8080/player/random/transfers';

  constructor(private http: HttpClient) { }
}
