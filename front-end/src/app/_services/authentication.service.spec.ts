import { TestBed, inject } from '@angular/core/testing';

import {AuthenticationService} from './authentication.service';

describe('AuthenticationService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AuthenticationService]
    });
  });

  it('should be created', inject([AuthenticationService], (service: AuthenticationService) => {
    expect(service).toBeTruthy();
  }));

  it('should send the login request to the server', (done) => {
    done();
  });

  it('should should receive a observable/promise /w error checking', (done) => {
    done();
  });

});
