import {Component} from '@angular/core';
import {RouterOutlet} from "@angular/router";
import {GameJourneyComponent} from "./components/game-journey/game-journey.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterOutlet,
    GameJourneyComponent
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
}
