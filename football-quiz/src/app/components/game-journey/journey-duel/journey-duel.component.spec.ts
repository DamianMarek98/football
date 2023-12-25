import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JourneyDuelComponent } from './journey-duel.component';

describe('JourneyDuelComponent', () => {
  let component: JourneyDuelComponent;
  let fixture: ComponentFixture<JourneyDuelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [JourneyDuelComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(JourneyDuelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
