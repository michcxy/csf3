import { Injectable, inject } from '@angular/core';
import { Order } from './models';
import { HttpClient, HttpHeaders } from '@angular/common/http';

export class PizzaService {

  http = inject(HttpClient)

  orders : Order[] = []
  email : string = ''

  
  // TODO: Task 3
  // You may add any parameters and return any type from placeOrder() method
  // Do not change the method name
  placeOrder(order: Order) {
  
    // order.toppings=Object.keys(order.toppings).filter(key => order.toppings[key]);
    // console.info(order.toppings)
    // console.info(order)

    return this.http.post<any>('/api/order', order)

      //const headers = new HttpHeaders({ 'Content-Type': 'application/json' });

    //{ headers }

  }

  // TODO: Task 5
  // You may add any parameters and return any type from getOrders() method
  // Do not change the method name
  getOrders(order: Order) {
    return this.http.get<any>('/api/orders/${order.email}').subscribe((response) => {
      this.orders = response;
    })

    
  }

  // TODO: Task 7
  // You may add any parameters and return any type from delivered() method
  // Do not change the method name
  delivered(order: Order) {
    return this.http.get<any>('/api/order/${order.orderId}').subscribe((response) => {
      this.orders = response;
   })
  }

  deleteOrder(order: Order){
    return this.http.delete('/api/order/${order.orderId}');
  }

  setOrder(order: Order) {
    this.orders.push(order)
  }

}