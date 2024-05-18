import {Injectable} from "@angular/core";

@Injectable({
    providedIn: 'root'
})

export class GeolocationService {
    private latitude: number | undefined;
    private longitude: number | undefined;

    public getLocation(): Promise<any> {
        return new Promise((resolve, reject) => {
            if (!navigator.geolocation) {
                reject('Geolocation is not supported by this browser.');
            } else {
                navigator.geolocation.getCurrentPosition(resolve, reject);
            }
        });
    }

    public saveLocation(latitude: number, longitude: number): void {
        localStorage.setItem('userLocation', JSON.stringify({latitude, longitude}));
        this.latitude = latitude;
        this.longitude = longitude;
    }

    getLatitude() {
        return this.latitude;
    }

    getLongitude() {
        return this.longitude;
    }
}
