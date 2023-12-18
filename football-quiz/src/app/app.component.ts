import {Component} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterOutlet} from '@angular/router';
import {PlayerServiceService} from "./services/player-service.service";
import {Transfer} from "./model/transfer";
import {SharedModule} from "primeng/api";
import {TableModule} from "primeng/table";
import {PlayerSearchComponent} from "./components/player-search/player-search.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, SharedModule, TableModule, PlayerSearchComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  transfers: Transfer[] = [];

  constructor(private playerServiceService: PlayerServiceService) {
  }

  ngOnInit() {
    this.playerServiceService.getTransfers()
      .subscribe(transfers => this.transfers = transfers);
  }

  guess(event: any): void {

  }
}
