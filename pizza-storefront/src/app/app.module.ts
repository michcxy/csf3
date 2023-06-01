import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http'

import { AppComponent } from './app.component';
import { MainComponent } from './components/main.component';
import { OrdersComponent } from './components/orders.component';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { PizzaService } from './pizza.service';

const appRoutes: Routes = [
  {path: '', component: MainComponent},
  {path: 'orders', component: OrdersComponent},
  {path: 'orders/:email', component: OrdersComponent},
  {path: '**', redirectTo: '/', pathMatch:'full'} 
]

@NgModule({
  declarations: [
    AppComponent,
    MainComponent,
    OrdersComponent,
  ],
  imports: [
    BrowserModule, RouterModule.forRoot(appRoutes, {useHash : true}), ReactiveFormsModule, HttpClientModule
  ],

  providers: [ PizzaService],
  bootstrap: [AppComponent]
})
export class AppModule { }
