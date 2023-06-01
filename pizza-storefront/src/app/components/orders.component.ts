import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { PizzaService } from '../pizza.service';
import { Order } from '../models';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent {
  
  router = inject(Router)

  pizzaSvc = inject(PizzaService)

  orders : any

  ngOnInit(): void {
    this.orders = this.getOrderFromService
  }

  getOrderFromService(order: Order) {
    const orderList = this.pizzaSvc.getOrders(order);
    // Use the order object in your component
    this.orders = orderList
  }
 
}
