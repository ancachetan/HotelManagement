import {Component, OnInit} from '@angular/core';
import {NgClass, NgForOf, NgIf} from "@angular/common";
import {BookingService} from "../../services/booking.service";
import {HttpClientModule} from "@angular/common/http";
import {BookingWithDetails} from "../../models/BookingWithDetails";
import {RoomService} from "../../services/room-service";
import {Room} from "../../models/Room";
import {
  NgbAccordionBody,
  NgbAccordionButton,
  NgbAccordionCollapse,
  NgbAccordionDirective,
  NgbAccordionHeader,
  NgbAccordionItem,
  NgbNav,
  NgbNavItem,
  NgbNavLinkBase
} from "@ng-bootstrap/ng-bootstrap";
import {NavigationBarComponent} from "../../components/navigation-bar/navigation-bar.component";
import {DateUtils} from "../../utils/DateUtils";
import {FormsModule} from "@angular/forms";
import {FeedbackRequest} from "../../models/FeedbackRequest";
import {FeedbackService} from "../../services/feedback.service";

@Component({
  selector: 'app-bookings-page',
  standalone: true,
  imports: [
    NgClass,
    NgForOf, HttpClientModule, NgbAccordionBody, NgbAccordionButton, NgbAccordionCollapse, NgbAccordionDirective, NgbAccordionHeader, NgbAccordionItem, NgIf, NgbNav, NgbNavItem, NgbNavLinkBase, NavigationBarComponent, FormsModule
  ],
  providers: [BookingService, RoomService, FeedbackService],
  templateUrl: './bookings-page.component.html',
  styleUrl: './bookings-page.component.css'
})
export class BookingsPageComponent implements OnInit {
  protected bookings: BookingWithDetails[] = [];
  protected loggedUserId: number | undefined;
  protected availableRooms: Room[] = [];
  protected selectedHotelId: number | undefined;
  protected selectedBookingId: number | undefined;
  protected today: string | undefined;
  protected isFeedbackInputActive: boolean = false;
  protected feedbackComment: string | undefined;

  constructor(private bookingService: BookingService,
              private roomService: RoomService,
              private feedbackService: FeedbackService,
              private dateUtils: DateUtils) {
  }

  ngOnInit() {
    this.today = this.dateUtils.getTodayDate();
    this.loggedUserId = Number(sessionStorage.getItem('userId')) ?? undefined;
    if (this.loggedUserId) {
      this.bookingService.getAllByUserId(this.loggedUserId).subscribe({
        next: bookings => {
          this.bookings = bookings;
        }
      })
    }
  }

  protected cancelBooking(bookingId: number) {
    this.bookingService.cancel(bookingId).subscribe({
      next: () => {
        const booking = this.bookings.find(booking => booking.id === bookingId)
        if (booking) {
          booking.canceled = true;
        }
        this.selectedHotelId = undefined;
        this.selectedBookingId = undefined;
        alert('Booking canceled successfully!')
      },
      error: () => {
        alert('Booking could not be canceled due to the late hour before check-in time')
      }
    })
  }

  protected findAvailableRooms(booking: BookingWithDetails) {
    this.selectedHotelId = booking.hotelId;
    this.selectedBookingId = booking.id;
    this.availableRooms = []
    this.roomService.getAllAvailableInSpecifiedInterval(booking.hotelId!, booking.checkInDate!, booking.checkOutDate!).subscribe({
      next: rooms => {
        this.availableRooms = rooms;
      }
    })
  }

  protected changeRoom(room: Room) {
    this.bookingService.updateRoom(this.selectedBookingId!, room.id!).subscribe({
      next: () => {
        const booking = this.bookings.find(book => book.id === this.selectedBookingId!)
        if (booking) {
          booking.roomType = room.type;
          booking.price = room.price;
        }
        this.selectedHotelId = undefined;
        this.selectedBookingId = undefined;
        alert('Booking updated successfully!')
      },
      error: () => {
        alert('Booking could not be updated due to the late hour before check-in time')
      }
    })
  }

  protected isFeedbackAvailable(booking: BookingWithDetails) {
    return booking.checkOutDate! <= this.today! && !booking.canceled;
  }

  protected cancelRoomUpdate() {
    this.availableRooms = []
  }

  protected showFeedbackInput(booking: BookingWithDetails) {
    this.selectedBookingId = booking.id
    this.selectedHotelId = booking.hotelId
    this.isFeedbackInputActive = true
  }

  protected addFeedback() {
    let feedback = new FeedbackRequest()
    feedback.comment = this.feedbackComment
    feedback.hotelId = this.selectedHotelId
    feedback.userId = this.loggedUserId
    this.feedbackService.add(feedback).subscribe({
      next: () => {
        alert('Feedback added successfully')
        this.isFeedbackInputActive = false
      }
    })
  }
}
