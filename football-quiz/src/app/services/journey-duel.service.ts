import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class JourneyDuelService {
  private readonly API_URL = '/api/v1/journey-games';

  constructor(private http: HttpClient) {
  }

  public create(): Observable<void> {
    return this.http.post<void>(this.API_URL + '/create', {});
  }

  public exists(): Observable<Boolean> {
    return this.http.get<Boolean>(this.API_URL + '/exists');
  }

  public join(name: String): Observable<string> {
    return this.http.post<string>(this.API_URL + '/join/' + name, {});
  }

  public players(): Observable<string[]> {
    return this.http.post<string[]>(this.API_URL + '/players', {});
  }

  public start(): Observable<void> {
    return this.http.post<void>(this.API_URL + '/start', {});
  }
}
