import { Component, OnInit } from '@angular/core';
import {AuthService} from "../auth/auth.service";
import {Weather} from "../weather-payload";
import {ActivatedRoute, Router} from "@angular/router";
import {WeatherService} from "../weather.service";
import {Observable} from "rxjs";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  weather!: Weather
  city = "singapore"


  constructor(public authSvc: AuthService,
              private weatherSvc: WeatherService) { }

  ngOnInit(): void {
    this.city = this.city

    this.weatherSvc.getWeather(this.city).subscribe((data:Weather) =>{
      this.weather = data
      console.log(">>>>data",data)
    })
  }

  logout() {
    this.authSvc.logout()
  }
}
