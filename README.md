# Taco-Loco-Java-Challenge
Taco Loco, a quick service fleet of taco trucks, is building a new mobile app to enable their customers to place orders for their delicious tacos. They’ve hired Detroit Labs to build the backend services to power this app. As a backend developer, you’ve been tasked with creating a web service to calculate order totals. The service will take as input the items and quantities ordered, and respond with the order total. Taco Loco’s menu consists of the the following items: Veggie Taco, $2.50 ea. Chicken or Beef Taco, $3.00 ea. Chorizo Taco, $3.50 ea. If a customer orders 4 or more tacos, then a 20% discount should be applied to the entire order.

# Documentation
------- After intitilizng my project using Maven to build out my Java template, I created an Item class and Shopping Cart class to model the data I wanted to recieve and send back.


public class Item {
    
    private final UUID id;

    private final String name;

    public final double price;

    private final int quantity;

    public Item(@JsonProperty("id") UUID id, @JsonProperty("name") String name, @JsonProperty("price") double price, @JsonProperty("quantity") int quantity){
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    
    public UUID getId(){
        return id;
    }

    public String getName(){
        return name;
    }
    
    public double getPrice(){
      return price;
    }

    public int getQuantity(){
        return quantity;
    }
}

public class ShoppingCart {
    
    public final List<Item> Cart;

    public final String Total;

    public ShoppingCart(List<Item> Cart, String Total){
        this.Cart = Cart;
        this.Total = Total;
    }
}


---- then I built out an Item Dao to act as an interface and control how I would be inserting my Item objects into my mock database.


public interface ItemDao {

    int insertItem(UUID id, Item item);

    default int insertItem(Item item){
        UUID id = UUID.randomUUID();
        return insertItem(id, item);
    }

    ShoppingCart returnCart();

}

---- then I created an Item Service to create the functionality I could implement in my API controller

@Service
public class ItemService {

    private final ItemDao itemDao;

    @Autowired
    public ItemService(@Qualifier("itemDao") ItemDao itemDao){
        this.itemDao = itemDao;
    }
    public int addItem(Item item){
        return itemDao.insertItem(item);
    }
    
    public ShoppingCart returnCart(){
        return itemDao.returnCart();
    }
}

---- I then created a mock Data Access Service to simulate adding items to a database and return a "shopping cart" with all items purchased and the total with discounts applied

@Repository("itemDao")
public class ItemDataAccessService implements ItemDao {
    
    private static List<Item>  DB = new ArrayList<>();

    @Override
    public int insertItem(UUID id, Item item){
        
        DB.add(new Item(id, item.getName(), item.getPrice(),item.getQuantity()));
        return 1;
    }
    
    @Override
    public ShoppingCart returnCart(){

        int totalQuantity = 0;
        
        double totalCost = 0.00;

        for(Item item : DB){
            totalQuantity += item.getQuantity();
            totalCost += (item.getPrice() * item.getQuantity());
        }
        if(totalQuantity >= 4){
            totalCost = totalCost * .8;
        }
        
        String result = String.format("%.2f", totalCost);
        
        ShoppingCart cart = new ShoppingCart(DB, result);

        return cart;

    }

}

---- I then created my REST controller, implememnting a post request to send items up to the database and a get request to get back all purchased items and the total cost


@RequestMapping("api/item")
@RestController
public class ItemController {
    
    private final ItemService itemService;
    
    @Autowired
    public ItemController(ItemService itemService){
        this.itemService = itemService;
    }
    
    @PostMapping
    public void addItem(@RequestBody Item item){
        itemService.addItem((item));
        
    }

    @GetMapping
    public ShoppingCart getCart(){
        return itemService.returnCart();
    }
    
}

---- Finally, I used MockMvc to imlement basic testing on my API controller, testing the get and post requests.

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

