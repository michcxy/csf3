package ibf2022.batch3.assessment.csf.orderbackend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;

import ibf2022.batch3.assessment.csf.orderbackend.models.PizzaOrder;
import ibf2022.batch3.assessment.csf.orderbackend.respositories.OrdersRepository;
import ibf2022.batch3.assessment.csf.orderbackend.respositories.PendingOrdersRepository;
import ibf2022.batch3.assessment.csf.orderbackend.services.OrderingService;

@Controller
@RequestMapping("/api")
public class OrderController {

	@Autowired
	OrdersRepository orderRepo;

	@Autowired
	PendingOrdersRepository pendingOrderRepo;

	@Autowired
	OrderingService orderSvc;

	// TODO: Task 3 - POST /api/order
	@PostMapping(path="/order", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> placeOrder(@RequestBody PizzaOrder order, @RequestBody List<String> toppings) throws JsonProcessingException {
		
		System.out.println("ordering");
		System.out.println("toppings" + order.getToppings());
		System.out.println(toppings);
		
		PizzaOrder pizzaOrder = new PizzaOrder();
		pizzaOrder.setName(order.getName());
		pizzaOrder.setEmail(order.getEmail());
		pizzaOrder.setSize(order.getSize());
		pizzaOrder.setThickCrust(order.getThickCrust());
		pizzaOrder.setSauce(order.getSauce());
		pizzaOrder.setToppings(order.getToppings());
		pizzaOrder.setComments(order.getComments());

		try{
			orderRepo.add(pizzaOrder);
			
			pendingOrderRepo.add(pizzaOrder);
			String orderString = pendingOrderRepo.add(pizzaOrder);

			return ResponseEntity
            .status(HttpStatus.ACCEPTED)
            .body(orderString);

		}catch(Exception e) {
            // Return 500 Internal Server Error with error message
            String errorMessage = "An error occurred.";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(errorMessage);
		}
    
	}

	// TODO: Task 6 - GET /api/orders/<email>
	@GetMapping(path="/orders/{email}")
	public List<PizzaOrder> getOrdersByEmail(@PathVariable String email){
		List<PizzaOrder> orders = orderSvc.getPendingOrdersByEmail(email);
        return orders;
	}

	// TODO: Task 7 - DELETE /api/order/<orderId>
	@DeleteMapping("/order/{orderId}")
    public boolean deleteOrder(@PathVariable String orderId) {
        return orderSvc.markOrderDelivered(orderId);
		
    }
}
