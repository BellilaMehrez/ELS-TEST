import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class DistinctService {

  constructor(private http: HttpClient) {
  }

  distinct(file: any, distinct: string): Observable<any> {
    let body = new FormData();
    body.append("file", file);
    return this.http.post('http://localhost:8080/api/distinct?criteria=' + distinct, body);
  }
}
