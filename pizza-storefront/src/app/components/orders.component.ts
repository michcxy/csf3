import { Component, Input, inject } from '@angular/core';
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

  delivered: boolean = false

  @Input()
  orders : any

  ngOnInit(): void {
    this.orders = this.getOrderFromService
  }

  getOrderFromService(order: Order) {
    const orderList = this.pizzaSvc.getOrders(order);
    this.orders = orderList
  }
  
  pizzaDelivered(){
    this.delivered = true;
  }


}
