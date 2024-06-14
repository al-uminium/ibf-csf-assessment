import { Component, OnInit, inject } from '@angular/core';
import { WebcamImage } from 'ngx-webcam';
import { PictureService } from '../picture.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UploadService } from '../upload.service';

@Component({
  selector: 'app-picture',
  templateUrl: './picture.component.html',
  styleUrl: './picture.component.css'
})
export class PictureComponent implements OnInit{

  // TODO: Task 2
  webcamImage!: WebcamImage;
  fb!: FormGroup
  private readonly pictureSvc = inject(PictureService)
  private readonly router = inject(Router)

  ngOnInit(): void {
    this.pictureSvc.img$.subscribe((img) => {
      this.webcamImage = img;
    })
    this.fb = new FormGroup({
      title: new FormControl("", Validators.compose([Validators.required, Validators.minLength(5)])),
      comments: new FormControl()
    })
  }

  handleBack() {
    const isDiscard = confirm("Do you want to discard the photo?");
    (isDiscard) ? this.router.navigate([""]) : console.info("Staying in view 2");
  }

  // TODO: Task 3
  private readonly uploadSvc = inject(UploadService);

  handleSubmit() {
    this.uploadSvc.upload(this.webcamImage, this.fb.value.title, this.fb.value.comments)
      .subscribe({
        next: () => this.router.navigate([""]),
        error: (e: Error) => {
          console.log(e);
          alert(e.message)
        }
      })
  }
}
