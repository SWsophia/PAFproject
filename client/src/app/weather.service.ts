import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Weather} from "./weather-payload";
import {lastValueFrom, Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class WeatherService {

  constructor(private http: HttpClient) { }

  getWeather(city:string): Observable<Weather> {
    return this.http.get<Weather>('http://localhost:8080/api/weather/'+city
      // this.http.get<Weather>(`/api/weather/${city}`)
    )
  }
}
