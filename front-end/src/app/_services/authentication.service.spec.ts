import { TestBed, inject } from '@angular/core/testing';

import {AuthenticationService} from './authentication.service';
import {LoginComponent} from "../login/login.component";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";

describe('AuthenticationService', () => {

  let component: LoginComponent;
  let service: AuthenticationService;
  let route: ActivatedRoute;
  let router: Router;
  let http: HttpClient;
  let spy: any;

  beforeEach(() => {
    service = new AuthenticationService(http);
    component = new LoginComponent(route, router, service);

    TestBed.configureTestingModule({
      providers: [AuthenticationService]
    });
  });

  afterEach(() => {
    service = null;
    component = null;
  });

  it('should fail if user is not authenticated ', () => {
    localStorage.setItem('token', '12345');
    spy = spyOn(service, 'serverAuth').and.returnValue(false);
    expect(component.onSubmit()).toBeFalsy();
  });

  it('should should receive a observable/promise /w error checking', (done) => {
    done();
  });


  it('should should receive a observable/promise /w error checking', (done) => {
    done();
  });


});
