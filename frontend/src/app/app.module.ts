import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { MainComponent } from './views/main.component';
import { PictureComponent } from './views/picture.component';
import { WebcamModule } from 'ngx-webcam';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent, MainComponent, PictureComponent
  ],
  imports: [
    BrowserModule, WebcamModule, ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
