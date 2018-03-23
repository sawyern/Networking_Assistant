import { TestBed, inject } from '@angular/core/testing';

import { StarServiceService } from './star-service.service';

describe('StarServiceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [StarServiceService]
    });
  });

  it('should be created', inject([StarServiceService], (service: StarServiceService) => {
    expect(service).toBeTruthy();
  }));
});
