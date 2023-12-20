import { Component } from '@angular/core';
import {JourneyGame} from "../../model/journey-game";
import {PlayerResult} from "../../model/search";
import {Message, SharedModule} from "primeng/api";
import {PlayerJourneyService} from "../../services/player-journey.service";
import {ButtonModule} from "primeng/button";
import {DividerModule} from "primeng/divider";
import {MessagesModule} from "primeng/messages";
import {NgForOf, NgIf} from "@angular/common";
import {PlayerSearchComponent} from "../player-search/player-search.component";
import {TableModule} from "primeng/table";
import {BreakpointObserver, Breakpoints} from "@angular/cdk/layout";
import {CardModule} from "primeng/card";
import {DataViewModule} from "primeng/dataview";

@Component({
  selector: 'app-game-journey',
  standalone: true,
  imports: [
    ButtonModule,
    DividerModule,
    MessagesModule,
    NgIf,
    PlayerSearchComponent,
    SharedModule,
    TableModule,
    CardModule,
    NgForOf,
    DataViewModule
  ],
  templateUrl: './game-journey.component.html',
  styleUrl: './game-journey.component.css'
})
export class GameJourneyComponent {
  journeyGame: JourneyGame | any;
  playerResult: PlayerResult | undefined;
  mobile: boolean = false;
  messages: Message[] = [];
  messageSuccess: Message = { severity: 'success', summary: 'Success', detail: 'That\'s right it\'s!' };
  messageWarn: Message = { severity: 'warn', summary: 'Nope', detail: 'That\'s not him!' };


  constructor(private playerJourneyService: PlayerJourneyService, private breakpointObserver: BreakpointObserver) {
    breakpointObserver.observe([
      Breakpoints.HandsetLandscape,
      Breakpoints.HandsetPortrait
    ]).subscribe(result => this.mobile = result.matches);
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
