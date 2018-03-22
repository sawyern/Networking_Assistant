import { TestBed, inject } from '@angular/core/testing';

import { GetAccountService } from './get-account.service';

describe('GetAccountService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [GetAccountService]
    });
  });

  it('should be created', inject([GetAccountService], (service: GetAccountService) => {
    expect(service).toBeTruthy();
  }));
});
