package ibf2022.batch3.assessment.csf.orderbackend.respositories;

import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.client.result.UpdateResult;

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
	}
	
	// TODO: Task 6
	// WARNING: Do not change the method's signature.
	// Write the native MongoDB query in the comment below
	//   Native MongoDB query here for getPendingOrdersByEmail()
	//  db.orders.find({ email: "example@example.com" })
	public List<PizzaOrder> getPendingOrdersByEmail(String email) {
		Query query = new Query(Criteria.where("email").is(email));
        return mongoTemplate.find(query, PizzaOrder.class, "orders");
	}

	// TODO: Task 7
	// WARNING: Do not change the method's signature.
	// Write the native MongoDB query in the comment below
	//   Native MongoDB query here for markOrderDelivered()
	// 	db.orders.updateOne(
	//    { _id: ObjectId("orderId") }, 
	//    { $set: { delivered: true } } 
	// )
	public boolean markOrderDelivered(String orderId) {
		Query query = new Query(Criteria.where("_id").is(orderId));
		Update update = new Update().set("delivered", true);
		UpdateResult updateResult = mongoTemplate.updateFirst(query, update, PizzaOrder.class);
		return updateResult.wasAcknowledged() && updateResult.getModifiedCount() > 0;
	}


}
