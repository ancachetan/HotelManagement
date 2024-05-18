import {Injectable} from "@angular/core";

@Injectable({
  providedIn: 'root'
})
export class DateUtils {
  private today: string | undefined;

  constructor() {
    this.setTodayDate()
  }

  public getTodayDate() {
    return this.today;
  }

  private setTodayDate() {
    const today = new Date();
    const year = today.getFullYear();
    const month = (today.getMonth() + 1).toString().padStart(2, '0');
    const day = today.getDate().toString().padStart(2, '0');
    this.today = `${year}-${month}-${day}`;
  }
}
