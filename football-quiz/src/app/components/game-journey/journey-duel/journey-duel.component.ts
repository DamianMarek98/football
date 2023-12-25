import { Component } from '@angular/core';
import {JourneyDuelService} from "../../../services/journey-duel.service";
import {InputTextModule} from "primeng/inputtext";
import {PaginatorModule} from "primeng/paginator";
import {NgIf} from "@angular/common";
import {TableModule} from "primeng/table";
import {ButtonModule} from "primeng/button";

@Component({
  selector: 'app-journey-duel',
  standalone: true,
  imports: [
    InputTextModule,
    PaginatorModule,
    NgIf,
    TableModule,
    ButtonModule
  ],
  templateUrl: './journey-duel.component.html',
  styleUrl: './journey-duel.component.css'
})
export class JourneyDuelComponent {
  gameExists: Boolean = false;
  players: String[] = [];
  joined: boolean = false;
  gameInProgress: boolean = false;
  name: String = '';

  constructor(private journeyDuelService: JourneyDuelService) {
    journeyDuelService.exists().subscribe(result => {
      this.gameExists = result;
      if (!this.gameExists) {
        journeyDuelService.create().subscribe(result => {
          this.gameExists = true;
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
}
