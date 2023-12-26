import {Injectable} from "@angular/core";
import {Subject} from "rxjs";

// Declare SockJS and Stomp
declare var SockJS: any;
declare var Stomp: any;

@Injectable({
  providedIn: 'root'
})
export class MessageService {
  stompClient: any;
  private _playersSubject = new Subject<string[]>();


  constructor() {
    const serverUrl = 'http://localhost:8080/path';
    const ws = new SockJS(serverUrl);
    this.stompClient = Stomp.over(ws);
    this.stompClient.connect({}, (frame: any) => {
      console.log('connected');
      this.stompClient.subscribe('/topic/players', (message: any) => {
        if (message.body) {
          this._playersSubject.next(JSON.parse(message.body));
        }
      });
    });
  }


  get playersSubject(): Subject<string[]> {
    return this._playersSubject;
  }
}
