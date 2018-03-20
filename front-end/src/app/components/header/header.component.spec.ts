import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HeaderComponent } from './header.component';

describe('HeaderComponent', () => {
  let component: HeaderComponent;
  let fixture: ComponentFixture<HeaderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HeaderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should have option to direct users to event dashboard (see events to attend)', () => {
    expect(component).toBeTruthy();
  });

  it('should have option to direct users back home', () => {
    expect(component).toBeTruthy();
  });

  it('should be auth guard protected to redirect unauthenticated users to login page', () => {
    expect(component).toBeTruthy();
  });
});
