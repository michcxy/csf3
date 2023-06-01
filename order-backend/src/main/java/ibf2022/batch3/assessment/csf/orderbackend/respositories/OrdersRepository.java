package ibf2022.batch3.assessment.csf.orderbackend.respositories;

import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import ibf2022.batch3.assessment.csf.orderbackend.models.PizzaOrder;

@Repository
public class OrdersRepository {

	@Autowired
	MongoTemplate mongoTemplate;

	// TODO: Task 3
	// WARNING: Do not change the method's signature.
	// Write the native MongoDB query in the comment below
	//   Native MongoDB query here for add()
	// db.orders.insert({
	// 	_id: "id",
	// 	date: date,
    // 	total: "total",
    // 	name: "name",
    //  email: "email",
	//  base: "base",
	//  sauce: "sauce"
	//  size: "size"
	//  comments: "comments"
    // 	toppings ["topping1", "topping2"]
	// })
	public void add(PizzaOrder order) {
		Document document = new Document();
			document.append("_id", order.getOrderId());
			document.append("date", order.getDate());
			document.append("total", order.getTotal());
			document.append("name", order.getName());
			document.append("email", order.getEmail());
			document.append("base", order.getThickCrust());
			document.append("sauce", order.getSauce());
			document.append("size", order.getSize());
			document.append("comments", order.getComments());
			document.append("toppings", order.getToppings());

			mongoTemplate.insert(document, "orders");
			System.out.println("inserting");
			System.out.println(order.getToppings());
	}
	
	// TODO: Task 6
	// WARNING: Do not change the method's signature.
	// Write the native MongoDB query in the comment below
	//   Native MongoDB query here for getPendingOrdersByEmail()
	public List<PizzaOrder> getPendingOrdersByEmail(String email) {

		return null;
	}

	// TODO: Task 7
	// WARNING: Do not change the method's signature.
	// Write the native MongoDB query in the comment below
	//   Native MongoDB query here for markOrderDelivered()
	public boolean markOrderDelivered(String orderId) {

		return false;
	}


}
