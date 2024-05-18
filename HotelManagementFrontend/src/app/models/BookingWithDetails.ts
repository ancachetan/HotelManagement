import {RoomType} from "./RoomType";

export class BookingWithDetails {
  id: number | undefined;
  checkInDate: string | undefined;
  checkOutDate: string | undefined;
  canceled: boolean | undefined;
  roomType: RoomType | undefined;
  hotelName: string | undefined;
  hotelId: number | undefined;
  price: number | undefined;
}
