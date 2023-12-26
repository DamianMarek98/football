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

  constructor(private journeyDuelService: JourneyDuelService, private messageService: MessageService) {
    messageService.playersSubject.subscribe((players: string[]) => this.players = players);
    messageService.gameStartedSubject.subscribe((transfers: JourneyTransfer[]) => {
      this.gameInProgress = true;
      this.transfers = transfers;
    });

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
}
