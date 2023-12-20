import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GameJourneyComponent } from './game-journey.component';

describe('GameJourneyComponent', () => {
  let component: GameJourneyComponent;
  let fixture: ComponentFixture<GameJourneyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GameJourneyComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(GameJourneyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
