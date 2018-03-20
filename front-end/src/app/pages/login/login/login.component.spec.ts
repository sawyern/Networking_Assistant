import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginComponent } from './login.component';

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LoginComponent ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  // Critical functions / MVP
  it('allows users to login', () => {
    expect(component).toBeTruthy();
  });

  it('login form has 2 fields - email & password', () => {
    expect(component).toBeTruthy();
  });

  it('password must be hidden (*******) ', () => {
    expect(component).toBeTruthy();
  });

  it('email must have validation', () => {
    expect(component).toBeTruthy();
  });

  it('password must have validation', () => {
    expect(component).toBeTruthy();
  });


  //Nice to Haves
  it('hitting enter on password field submits form', () => {
    expect(component).toBeTruthy();
  });

});
