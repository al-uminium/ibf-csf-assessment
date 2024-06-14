import { Injectable } from '@angular/core';
import { WebcamImage } from 'ngx-webcam';
import { BehaviorSubject, Observable, ReplaySubject, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PictureService {
  img: ReplaySubject<WebcamImage> = new ReplaySubject<WebcamImage>;
  img$ = this.img.asObservable();

  setImg(img: WebcamImage){
    this.img.next(img);
  }
}
