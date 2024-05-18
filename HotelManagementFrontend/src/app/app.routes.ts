import {RouterModule, Routes} from '@angular/router';
import {HomePageComponent} from "./pages/home-page/home-page.component";
import {NgModule} from "@angular/core";
import {HotelsPageComponent} from "./pages/hotels-page/hotels-page.component";
import {BookingsPageComponent} from "./pages/bookings-page/bookings-page.component";

export const routes: Routes = [
  {path: 'home', component: HomePageComponent},
  {path: 'hotels', component: HotelsPageComponent},
  {path: 'my-bookings', component: BookingsPageComponent},
  {path: '', component: HomePageComponent},
  {path: '**', component: HomePageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
