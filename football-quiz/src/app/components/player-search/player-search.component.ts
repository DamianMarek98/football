import {Component, OnInit} from '@angular/core';
import {PlayerSearchService} from "../../services/player-search.service";
import {PlayerResult} from "../../model/search";
import {debounceTime, Subject, switchMap} from "rxjs";
import {DropdownModule} from "primeng/dropdown";
import {ChipsModule} from "primeng/chips";
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'app-player-search',
  standalone: true,
  imports: [
    DropdownModule,
    ChipsModule,
    FormsModule
  ],
  templateUrl: './player-search.component.html',
  styleUrl: './player-search.component.css'
})
export class PlayerSearchComponent implements OnInit {
  foundPlayers: PlayerResult[] = [];
  searchText: string = '';

  private searchSubject = new Subject<string>();

  constructor(private playerSearchService: PlayerSearchService) {
  }

  ngOnInit() {
    this.searchSubject.pipe(
      debounceTime(3000),
      switchMap(searchText => this.playerSearchService.searchPlayers(searchText))
    ).subscribe(response => this.foundPlayers = response.players)
  }

  searchPlayers(event: any) {
    if (event.keyCode == 13) {
      return;
    }
    this.searchSubject.next(event.target.value);
  }
}
