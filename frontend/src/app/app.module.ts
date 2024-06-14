import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { MainComponent } from './views/main.component';
import { PictureComponent } from './views/picture.component';
import { WebcamModule } from 'ngx-webcam';
import { ReactiveFormsModule } from '@angular/forms';
import { PictureService } from './picture.service';
import { UploadService } from './upload.service';
import { RouterModule, Routes } from '@angular/router';
import { provideHttpClient } from '@angular/common/http';

const appRoutes: Routes = [
  {path: "", component: MainComponent},
  {path: "picture", component:PictureComponent}
]

@NgModule({
  declarations: [
    AppComponent, MainComponent, PictureComponent
  ],
  imports: [
    BrowserModule, WebcamModule, ReactiveFormsModule, RouterModule.forRoot(appRoutes, { useHash: true })
  ],
  providers: [PictureService, UploadService, provideHttpClient()],
  bootstrap: [AppComponent]
})
export class AppModule { }
