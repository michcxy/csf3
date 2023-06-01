package ibf2022.batch3.assessment.csf.orderbackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import ibf2022.batch3.assessment.csf.orderbackend.models.PizzaOrder;
import ibf2022.batch3.assessment.csf.orderbackend.respositories.OrdersRepository;
import ibf2022.batch3.assessment.csf.orderbackend.respositories.PendingOrdersRepository;

@Service
public class OrderingService {

	@Autowired
	private OrdersRepository ordersRepo;

	@Autowired
	private PendingOrdersRepository pendingOrdersRepo;

	private static String URL = "https://pizza-pricing-production.up.railway.app";
	
	// TODO: Task 5
	// WARNING: DO NOT CHANGE THE METHOD'S SIGNATURE
	public PizzaOrder placeOrder(PizzaOrder order) throws OrderException {
		String orderUrl = UriComponentsBuilder.fromUriString(URL)
            .queryParam("name", order.getName())
            .queryParam("email", order.getEmail())
            .queryParam("sauce", order.getSauce())
			.queryParam("size", order.getSize())
			.queryParam("thickCrust", order.getThickCrust())
			.queryParam("toppings", order.getToppings())
			.queryParam("comments", order.getComments())
            .toUriString();

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			headers.setAccept((List<MediaType>) MediaType.TEXT_PLAIN);
	
			MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
			body.add("name", order.getName());
			body.add("email", order.getEmail());
            body.add("sauce", order.getSauce());
			body.add("size", order.getSize().toString());
			body.add("thickCrust", order.getThickCrust().toString());
			body.add("toppings", order.getToppings().toString());
			body.add("comments", order.getComments());

			RequestEntity<MultiValueMap<String, String>> requestEntity = RequestEntity
                .post(orderUrl)
                .headers(headers)
                .body(body);

				RestTemplate restTemplate = new RestTemplate();
				ResponseEntity<String> response = restTemplate.exchange(requestEntity, String.class);
		
				if (response.getStatusCode() == HttpStatus.OK) {
					String responseBody = response.getBody();
					System.out.println(responseBody);
				}
			
			// RequestEntity<Void> req = RequestEntity
            //     .get(orderUrl)
            //     .setContentType(MediaType.APPLICATION_FORM_URLENCODED)
            //     .header("X-RapidAPI-Host", lcHost)
            //     .accept(MediaType.APPLICATION_JSON)
            //     .build();


		return null;
	}

	// For Task 6
	// WARNING: Do not change the method's signature or its implemenation
	public List<PizzaOrder> getPendingOrdersByEmail(String email) {
		return ordersRepo.getPendingOrdersByEmail(email);
	}

	// For Task 7
	// WARNING: Do not change the method's signature or its implemenation
	public boolean markOrderDelivered(String orderId) {
		return ordersRepo.markOrderDelivered(orderId) && pendingOrdersRepo.delete(orderId);
	}


}
