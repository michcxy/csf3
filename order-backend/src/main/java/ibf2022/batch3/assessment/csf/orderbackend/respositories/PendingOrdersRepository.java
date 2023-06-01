package ibf2022.batch3.assessment.csf.orderbackend.respositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ibf2022.batch3.assessment.csf.orderbackend.models.PizzaOrder;
import jakarta.json.Json;

@Repository
public class PendingOrdersRepository {

	@Autowired
	RedisTemplate redisTemplate;

	// TODO: Task 3
	// WARNING: Do not change the method's signature.
	public String add(PizzaOrder order) throws JsonProcessingException {

		PizzaOrder pizzaOrder = new PizzaOrder();

		pizzaOrder.setOrderId(order.getOrderId());
		pizzaOrder.setDate(order.getDate());
		pizzaOrder.setTotal(order.getTotal());
		pizzaOrder.setName(order.getName());
		pizzaOrder.setEmail(order.getEmail());

		String json = new ObjectMapper().writeValueAsString(pizzaOrder);

		redisTemplate.opsForValue().set("order:" + pizzaOrder.getOrderId(), json);

		return json;

	}

	// TODO: Task 7
	// WARNING: Do not change the method's signature.
	public boolean delete(String orderId) {
		return false;
	}

}
