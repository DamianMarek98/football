import {Injectable} from "@angular/core";

// Declare SockJS and Stomp
declare var SockJS: any;
declare var Stomp: any;

@Injectable({
  providedIn: 'root'
})
export class MessageService {
  stompClient: any;

  constructor() {
    const serverUrl = 'http://localhost:8080/path';
    const ws = new SockJS(serverUrl);
    this.stompClient = Stomp.over(ws);
    this.stompClient.connect({}, (frame: any) => {
      console.log('connected')
      this.stompClient.subscribe('/topic/players', (message: any) => {
        if (message.body) {
          console.log(message.body);
        }
      });
    });
  }
}
