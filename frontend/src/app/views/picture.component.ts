import { Component, OnInit } from '@angular/core';
import { WebcamImage } from 'ngx-webcam';

@Component({
  selector: 'app-picture',
  templateUrl: './picture.component.html',
  styleUrl: './picture.component.css'
})
export class PictureComponent implements OnInit{

  // TODO: Task 2
  webcamImage!: WebcamImage

  ngOnInit(): void {
    
  }
  // TODO: Task 3

}
