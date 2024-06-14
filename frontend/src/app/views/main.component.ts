import { Component, HostListener, OnInit, inject } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Observable, Subject, Subscription } from 'rxjs';
import { PictureService } from '../picture.service';
import { WebcamImage } from 'ngx-webcam';
import { Router } from '@angular/router';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrl: './main.component.css'
})
export class MainComponent implements OnInit {

  // TODO: Task 1
  height: number = 282
  width: number = 500
  fb!: FormGroup
  img: Subject<void> = new Subject<void>
  webCamImg!: WebcamImage
  aspectRatio: string[] = ["16:9", "4:3", "3:2", "1:1"]

  private readonly pictureSvc = inject(PictureService);
  private readonly router = inject(Router);

  ngOnInit(): void {
    this.fb = new FormGroup({
      ratio: new FormControl("16:9", Validators.required)
    })
  }

  handleAspectRatio($event: any) {
    const id = $event.target.id;
    console.log(id);
    switch(id) {
      case "16:9":
        this.height = 282
        break;
      case "4:3":
        this.height = 375
        break;
      case "3:2":
        this.height = 333
        break;
      case "1:1":
        this.height = 500
        break;

    }
  }

  handleSnap() {
    this.img.next()
  }

  handleImageCapture(webcamImage: WebcamImage) {
    this.pictureSvc.setImg(webcamImage);
    this.router.navigate(["picture"])
  }

}
