package com.example.TacoLoco;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import com.example.TacoLoco.api.ItemController;
import com.example.TacoLoco.model.Item;
import com.example.TacoLoco.model.ShoppingCart;
import com.example.TacoLoco.service.ItemService;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import ch.qos.logback.core.status.Status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(ItemController.class)
class TacoLocoApplicationTests {


    @Autowired
	public MockMvc mvc;
    
	@MockBean
	public ItemService itemService;

	@Test
	void getShoppingCart() throws Exception {

        List<Item> DB = new ArrayList<Item>();
		String Total = "0.00";
		ShoppingCart emptyCart = new ShoppingCart(DB, Total);
		Mockito.when(itemService.returnCart()).thenReturn(
			emptyCart
		);
		MvcResult result = mvc.perform(
            MockMvcRequestBuilders.get("http://localhost:8080/api/item")
			.accept(MediaType.APPLICATION_JSON)       
		).andReturn();
        
		System.out.println(result.getResponse());
		Mockito.verify(itemService).returnCart();
	}
    
	@Test
	public void createItem()throws Exception{
		UUID uuid = UUID.randomUUID();
		RequestBuilder request = MockMvcRequestBuilders
				.post("http://localhost:8080/api/item")
				.accept(MediaType.APPLICATION_JSON)
				.content("{\"name\":\"Chicken Taco\",\"price\":3.00,\"quantity\":3}")
                .contentType(MediaType.APPLICATION_JSON);
		
		
		MvcResult result = mvc.perform(request)
		.andExpect(status().isOk()).andReturn();
	}

	


	

		

	

}
