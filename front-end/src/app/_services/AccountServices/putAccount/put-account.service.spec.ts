import { TestBed, inject } from '@angular/core/testing';

import { PutAccountService } from './put-account.service';

describe('PutAccountService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PutAccountService]
    });
  });

  it('should be created', inject([PutAccountService], (service: PutAccountService) => {
    expect(service).toBeTruthy();
  }));
});
