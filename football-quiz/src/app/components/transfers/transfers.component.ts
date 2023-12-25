import {Component, Input} from '@angular/core';
import {DataViewModule} from "primeng/dataview";
import {DividerModule} from "primeng/divider";
import {NgForOf, NgIf} from "@angular/common";
import {SharedModule} from "primeng/api";
import {TableModule} from "primeng/table";
import {JourneyTransfer} from "../../model/journey-game";
import {PlayerJourneyService} from "../../services/player-journey.service";
import {BreakpointObserver, Breakpoints} from "@angular/cdk/layout";

@Component({
  selector: 'app-transfers',
  standalone: true,
    imports: [
        DataViewModule,
        DividerModule,
        NgForOf,
        NgIf,
        SharedModule,
        TableModule
    ],
  templateUrl: './transfers.component.html',
  styleUrl: './transfers.component.css'
})
export class TransfersComponent {
  @Input() transfers: JourneyTransfer[] = [];
  mobile: boolean = false;

  constructor(private breakpointObserver: BreakpointObserver) {
    breakpointObserver.observe([
      Breakpoints.HandsetLandscape,
      Breakpoints.HandsetPortrait
    ]).subscribe(result => this.mobile = result.matches);
  }
}
