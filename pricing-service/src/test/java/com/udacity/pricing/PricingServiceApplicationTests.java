package com.udacity.pricing;

import com.udacity.pricing.domain.price.Price;
import com.udacity.pricing.service.PricingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PricingServiceApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testGetPrice() throws Exception{
		Long vehicleId = 2L;
		Price price = getPrice(vehicleId);

		mvc.perform(get("/prices/" + vehicleId))
				.andExpect(status().isOk())
				.andExpect(jsonPath("vehicleId",is(price.getVehicleId().intValue())))
				.andExpect(jsonPath("currency",is(price.getCurrency())))
				.andExpect(jsonPath("price",is(price.getPrice().doubleValue())));
	}

	private Price getPrice(Long vehicleId){
		return PricingService.PRICES.get(vehicleId);
	}

}
