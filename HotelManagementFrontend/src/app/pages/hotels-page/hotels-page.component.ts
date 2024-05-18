import {Component, OnInit} from '@angular/core';
import {Hotel} from "../../models/Hotel";
import {HttpClientModule} from "@angular/common/http";
import {HotelService} from "../../services/hotel.service";
import {GeolocationService} from "../../services/geolocation-service";
import {FormsModule} from "@angular/forms";
import {NgForOf, NgIf} from "@angular/common";
import {UserService} from "../../services/user.service";
import {NgbAccordionModule, NgbModal, NgbModule} from "@ng-bootstrap/ng-bootstrap";
import {LoginComponent} from "../../components/login-component/login.component";
import {RegisterComponent} from "../../components/register-component/register.component";
import {Room} from "../../models/Room";
import {RoomService} from "../../services/room-service";
import {BookingService} from "../../services/booking.service";
import {BookingRequest} from "../../models/BookingRequest";
import {NavigationBarComponent} from "../../components/navigation-bar/navigation-bar.component";
import {DateUtils} from "../../utils/DateUtils";

@Component({
  selector: 'app-hotels-page',
  standalone: true,
  imports: [HttpClientModule, FormsModule, NgIf, NgForOf, NgbAccordionModule, NgbModule, NavigationBarComponent],
  providers: [HotelService, UserService, RoomService, BookingService],
  templateUrl: './hotels-page.component.html',
  styleUrl: './hotels-page.component.css',
})
export class HotelsPageComponent implements OnInit {
  protected radius: number = 0;
  protected hotels: Hotel[] = [];
  protected selectedHotel: Hotel | undefined;
  protected checkInDate: string | undefined;
  protected checkOutDate: string | undefined;
  protected loggedUserId: number | undefined;
  protected availableRooms: Room[] = [];
  protected today: string | undefined;


  constructor(
    private hotelService: HotelService,
    private geolocationService: GeolocationService,
    private modalService: NgbModal,
    private roomService: RoomService,
    private bookingService: BookingService,
    private dateUtils: DateUtils) {
  }

  ngOnInit() {
    this.today = this.dateUtils.getTodayDate();
    this.loggedUserId = Number(sessionStorage.getItem('userId')) ?? undefined;
  }

  protected onSearch() {
    if (this.geolocationService.getLatitude() && this.geolocationService.getLongitude()) {
      this.hotelService.getAllInSpecifiedRadius(this.radius, this.geolocationService.getLatitude()!, this.geolocationService.getLongitude()!).subscribe({
        next: data => {
          this.hotels = data;
        }
      })
    }
  }

  protected selectHotel(hotel: Hotel) {
    this.checkInDate = '';
    this.checkOutDate = ''
    this.availableRooms = [];
    this.selectedHotel = hotel;
  }

  protected cancelSelection() {
    this.selectedHotel = undefined;
    this.checkInDate = undefined;
    this.checkOutDate = undefined;
  }

  protected openLoginDialog() {
    const modalRef = this.modalService.open(LoginComponent);
    modalRef.dismissed.subscribe({
      next: () => {
        this.loggedUserId = Number(sessionStorage.getItem('userId')) ?? undefined;
      }
    })
  }

  protected openRegisterDialog() {
    const modalRef = this.modalService.open(RegisterComponent);
    modalRef.dismissed.subscribe({
      next: () => {
        this.loggedUserId = Number(sessionStorage.getItem('userId')) ?? undefined;
      }
    })
  }

  protected searchAvailableRooms() {
    if (this.selectedHotel) {
      this.roomService.getAllAvailableInSpecifiedInterval(this.selectedHotel.id!, this.checkInDate!, this.checkOutDate!).subscribe({
        next: rooms => {
          this.availableRooms = rooms;
        }
      })
    }
  }

  protected bookRoom(room: Room) {
    let bookingRequest = new BookingRequest();
    bookingRequest.roomId = room.id;
    bookingRequest.checkInDate = this.checkInDate;
    bookingRequest.checkOutDate = this.checkOutDate;
    bookingRequest.userId = this.loggedUserId;
    this.bookingService.add(bookingRequest).subscribe({
      next: () => {
        this.roomService.getAllAvailableInSpecifiedInterval(this.selectedHotel!.id!, this.checkInDate!, this.checkOutDate!).subscribe({
          next: rooms => {
            this.availableRooms = rooms;
          }
        })
      }
    })
  }
}
