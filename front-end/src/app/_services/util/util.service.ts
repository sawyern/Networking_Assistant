import { Injectable } from '@angular/core';

@Injectable()
export class UtilService {

  constructor() { }

  private serverUrl : string = "http://localhost:8080/";

  getServerUrl() : string {
    return this.serverUrl;
  }
}
