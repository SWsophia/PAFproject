import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Weather } from "./weather-payload";
import { lastValueFrom, Observable } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class WeatherService {

  constructor(private http: HttpClient) { }

  getWeather(city: string): Observable<Weather> {
    return this.http.get<Weather>('http://159.223.54.196:8080/api/weather/' + city
      // return this.http.get<Weather>('/api/weather/'+city
    )
  }
}
