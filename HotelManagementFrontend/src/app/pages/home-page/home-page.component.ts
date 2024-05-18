import {Component} from '@angular/core';
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import {NgStyle} from "@angular/common";
import {Router} from "@angular/router";

@Component({
    selector: 'app-home-page',
    standalone: true,
    imports: [NgbModule, NgStyle],
    templateUrl: './home-page.component.html',
    styleUrl: './home-page.component.css'
})
export class HomePageComponent {
    constructor(private router: Router) {

    }

    navigateToHotelsPage() {
        this.router.navigate(['/hotels'])
    }
}
