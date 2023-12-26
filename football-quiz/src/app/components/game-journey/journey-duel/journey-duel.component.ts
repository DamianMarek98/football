import { Component } from '@angular/core';
import {JourneyDuelService} from "../../../services/journey-duel.service";
import {InputTextModule} from "primeng/inputtext";
import {PaginatorModule} from "primeng/paginator";
import {NgIf} from "@angular/common";
import {TableModule} from "primeng/table";
import {ButtonModule} from "primeng/button";
import {MessageService} from "../../../services/messages.service";
import {JourneyTransfer} from "../../../model/journey-game";
import {TransfersComponent} from "../../transfers/transfers.component";
import {PlayerSearchComponent} from "../../player-search/player-search.component";
import {DividerModule} from "primeng/divider";
import {MessagesModule} from "primeng/messages";
import {PlayerResult} from "../../../model/search";
import {Message} from "primeng/api";
import {GameFinished} from "../../../model/journey-duel";


@Component({
  selector: 'app-journey-duel',
  standalone: true,
  imports: [
    InputTextModule,
    PaginatorModule,
    NgIf,
    TableModule,
    ButtonModule,
    TransfersComponent,
    PlayerSearchComponent,
    DividerModule,
    MessagesModule,
  ],
  templateUrl: './journey-duel.component.html',
  styleUrl: './journey-duel.component.css'
})
export class JourneyDuelComponent {
  gameExists: Boolean = false;
  gameOwner:  Boolean = false;
  players: string[] = [];
  joined: boolean = false;
  gameInProgress: boolean = false;
  name: String = '';
  transfers: JourneyTransfer[] = [];
  messages: Message[] = [];
  messageSuccess: Message = { severity: 'success', summary: 'Success', detail: 'That\'s right it\'s!' };
  messageWarn: Message = { severity: 'warn', summary: 'Nope', detail: 'That\'s not him!' };
  playerResult: PlayerResult | undefined;
  gameFinished = false;
  gameFinishInformation: GameFinished | undefined;
  won = false;


  constructor(private journeyDuelService: JourneyDuelService, private messageService: MessageService) {
    messageService.playersSubject.subscribe((players: string[]) => this.players = players);
    messageService.gameStartedSubject.subscribe((transfers: JourneyTransfer[]) => {
      this.gameInProgress = true;
      this.won = false;
      this.gameFinishInformation = undefined;
      this.messages = [];
      this.gameFinished = false;
      this.transfers = transfers;
    });
    messageService.gameFinishedSubject.subscribe((gameFinished: GameFinished) => {
      this.gameFinished = true;
      this.gameFinishInformation = gameFinished;
    })

    journeyDuelService.exists().subscribe(result => {
      this.gameExists = result;
      if (!this.gameExists) {
        journeyDuelService.create().subscribe(result => {
          this.gameExists = true;
          this.gameOwner = true;
        })
      }
    });
  }

  public join(): void {
    this.journeyDuelService.join(this.name)
      .subscribe(result => {
        localStorage.setItem('playerId', result);
        this.joined = true;
      });
  }

  public startGame(): void {
    this.journeyDuelService.start().subscribe();
  }

  public newGame(): void {
    this.journeyDuelService.create().subscribe(() => {
      this.journeyDuelService.start().subscribe();
    })
  }

  guess(event: PlayerResult): void {
    this.playerResult = event;
    let playerId = localStorage.getItem('playerId');
    if (!playerId) {
      playerId = '';
    }

    this.journeyDuelService.guess(event.id.toString(), playerId)
      .subscribe(result => {
        this.messages = [];
        if (result) {
          this.won = true;
          this.messageSuccess.detail = 'That\'s right it\'s ' + this.playerResult?.name;
          this.messages.push(this.messageSuccess);
        } else {
          this.messages.push(this.messageWarn);
        }
      });
  }
}
