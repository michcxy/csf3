import { Component, inject } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Order } from '../models';
import { PizzaService } from '../pizza.service';
import { firstValueFrom } from 'rxjs';
import { Router } from '@angular/router';

const SIZES: string[] = [
  "Personal - 6 inches",
  "Regular - 9 inches",
  "Large - 12 inches",
  "Extra Large - 15 inches"
]

const PIZZA_TOPPINGS: string[] = [
    'chicken', 'seafood', 'beef', 'vegetables',
    'cheese', 'arugula', 'pineapple'
]

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent {

  pizzaSize = SIZES[0]

  form!: FormGroup
  fb = inject(FormBuilder)
  router = inject(Router)

  pizzaSvc = inject(PizzaService)

  email: String = ''

  constructor() { }

  ngOnInit(): void {

    this.form = this.createForm()
  }

  updateSize(size: string) {
    this.pizzaSize = SIZES[parseInt(size)]
  }

  placeOrder() {
    const order: Order = this.form.value
    firstValueFrom(this.pizzaSvc.placeOrder(order))
    .then(result => {
      alert('ordered')
      this.form.reset()
    })
    .catch(err => {
      alert(JSON.stringify(err))
    })
    this.sendOrderToService(order)
    this.email = this.form.value['email']
    this.router.navigate(['/orders', this.email])
  }

  sendOrderToService(order: Order) {
    this.pizzaSvc.setOrder(order);
  }

  createForm(): FormGroup {
    let toppings = this.fb.group({})
    PIZZA_TOPPINGS.forEach(topping => {
      toppings.addControl(topping, this.fb.control<boolean>(false, [ Validators.required ]))
    })

    return this.fb.group({
      name: this.fb.control<string>('', [Validators.required]),
      email: this.fb.control<string>('', [Validators.required, Validators.email]),
      size: this.fb.control<number>(0, [Validators.required]),
      base: this.fb.control<string>('', [Validators.required]),
      sauce: this.fb.control<string>('', [Validators.required]),
      toppings: toppings,
      comments: this.fb.control<string>(''),
    })
  }

}
