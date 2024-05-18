import {Component, OnInit} from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {GeolocationService} from "./services/geolocation-service";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
  constructor(private geoLocationService: GeolocationService) {
  }

  ngOnInit() {
    this.geoLocationService.getLocation().then(position => {
      const latitude = position.coords.latitude;
      const longitude = position.coords.longitude;
      this.geoLocationService.saveLocation(latitude, longitude);
    }).catch(error => {
      console.error('Geolocation error:', error);
    });
  }
}
