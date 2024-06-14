import { HttpClient } from "@angular/common/http";
import { Injectable, inject } from "@angular/core";
import { dataToImage } from "./utils";
import { WebcamImage } from "ngx-webcam";

@Injectable({
  providedIn: 'root'
})
export class UploadService {

  // TODO: Task 3.
  // You may add add parameters to the method
  private readonly http = inject(HttpClient);
  private readonly url = "/api/image/upload"

  upload(img: WebcamImage, title: string, comments: string | undefined) {
    const imgFile = dataToImage(img.imageAsDataUrl);
    const formData = new FormData;
    formData.append('img', imgFile);
    formData.append('title', title);
    (typeof(comments) == "string") ? formData.append("comments", comments) : formData.append("comments", "");
    return this.http.post(this.url, formData)
  }
}
