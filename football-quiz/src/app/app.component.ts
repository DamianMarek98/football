import {Component} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterOutlet} from '@angular/router';
import {PlayerServiceService} from "./services/player-service.service";
import {Transfer} from "./model/transfer";
import {Message, SharedModule} from "primeng/api";
import {TableModule} from "primeng/table";
import {PlayerSearchComponent} from "./components/player-search/player-search.component";
import {PlayerJourneyService} from "./services/player-journey.service";
import {JourneyGame} from "./model/journey-game";
import {DividerModule} from "primeng/divider";
import {MessagesModule} from "primeng/messages";
import {PlayerResult} from "./model/search";
import {ButtonModule} from "primeng/button";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, SharedModule, TableModule, PlayerSearchComponent, DividerModule, MessagesModule, ButtonModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  journeyGame: JourneyGame | any;
  playerResult: PlayerResult | undefined;
  messages: Message[] = [];
  messageSuccess: Message = { severity: 'success', summary: 'Success', detail: 'That\'s right it\'s!' };
  messageWarn: Message = { severity: 'warn', summary: 'Nope', detail: 'That\'s not him!' };


  constructor(private playerJourneyService: PlayerJourneyService) {
  }

  ngOnInit() {
    this.startNewJourneyGame();
  }

  startNewJourneyGame() {
    this.playerResult = undefined;
    this.messages = [];
    this.playerJourneyService.startJourneyGame()
      .subscribe(journeyGame => this.journeyGame = journeyGame);
  }

  guess(event: PlayerResult): void {
    this.playerResult = event;
    this.playerJourneyService.makeGuess(this.journeyGame.id, event.id.toString())
      .subscribe(result => {
        this.messages = [];
        if (result) {
          this.messageSuccess.detail = 'That\'s right it\'s ' + this.playerResult?.name;
          this.messages.push(this.messageSuccess);
        } else {
          this.messages.push(this.messageWarn);
        }
      });
  }
}
