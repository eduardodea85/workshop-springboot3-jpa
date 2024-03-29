package com.dea.course.config;

//Estamos trabalhando com perfil de teste e em aplication.properties nós definimos isso usando nome do perfil como test. então podemos fazer configurações especificas para esse perfil como foi o caso de definir o arquivo aplication-test-properties. O próprio nome do arquivo já identifica o perfil e fizemos as configurações do banco de dados H2. Agora vamos criar essa classe de configuração.

//Esta classe de configuração não é nem controller, nem service e nem repository, ela vai ser uma classe auxiliar que vai fazer alguma configurações na minha aplicação. Isso é uma pratica muito utilizada.

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.dea.course.entities.Category;
import com.dea.course.entities.Order;
import com.dea.course.entities.OrderItem;
import com.dea.course.entities.Payment;
import com.dea.course.entities.Product;
import com.dea.course.entities.User;
import com.dea.course.entities.enums.OrderStatus;
import com.dea.course.repositories.CategoryRepository;
import com.dea.course.repositories.OrderItemRepository;
import com.dea.course.repositories.OrderRepository;
import com.dea.course.repositories.ProductRepository;
import com.dea.course.repositories.UserRepository;

@Configuration //Para falar para o spring que essa é uma classe especifica de configuração, colocamos essa anotation.
@Profile("test") //Para falar que essa classe é uma configuração especifica para o perfil de teste, coloco essa anotation com o mesmo nome que colocamos no profile de application.properties. Aí o spring consegue identificar que ele vai rodar essa configuração, somente quando estiver no perfil de test.
public class TestConfig implements CommandLineRunner{ //Essa classe de configuração vai servir para a gente fazer o nosso database seeding, ou seja, para popular o nosso banco de dados com alguns objetos. No caso os objetos abaixo u1 e u2. Mas para eu popular o banco de dados, vou precisar salvar os dados. Para salvar os dados, a classe que faz isso é o repository. Nesse momento, vou ter o meu primeiro caso de injeção de dependencia, porque essa classe de TestConfig vai precisar ter uma dependencia para o meu UserReposiry. E na POO aprendemos que quando um serviço depende de outro, essa dependencia precisa ser desaclopada. Então essa injeção de dependencia desaclopada ela pode ser feita manualmente por meio de um construtor, ou método set... Mas quando utilizamos um framework, geralmente tem um mecanismo de injeção de dependencia automático.
	//Quando implemento o CommanLineRunner, eu consigo executar a aplicação quando o programa for iniciado. Ele cria o método run abaixo e tudo que colocarmos dentro dele vai ser executado quando a aplicação for inciada.
	
	
	@Autowired //Só com essa anotação, o próprio spring na hora que tiver rodando a aplicação, vai resolver a dependência e associar uma instância de userRepository.
	
	private UserRepository userRepository; //Para fazer um objeto depender de outro precisa declarar a dependência com um atributo e para que o spring consiga resolver essa dependêcia e associar uma instância do userRepository no meu TestConfig, basta colocar o anotation @Autowired
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired//Anotação para injeção de dependecia
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;

	@Override
	public void run(String... args) throws Exception { //tudo que colocarmos dentro desse método vai ser executado quando a aplicação for inciada.
		
		Category cat1 = new Category(null, "Electronics"); //Instanciado esses 3 objetos da categoria.
		Category cat2 = new Category(null, "Books"); 
		Category cat3 = new Category(null, "Computers");
		
		Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, ""); 
		Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, ""); 
		Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, ""); 
		Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, ""); 
		Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));//Salvar no banco de dados.
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));//salva no banco de dados.
		
		p1.getCategories().add(cat2);//faço a relação entre as tabelas do banco de dados. Estou pegando um produto p1 e adiconando a uma categoria.
		p2.getCategories().add(cat1);
		p2.getCategories().add(cat3);
		p3.getCategories().add(cat3);
		p4.getCategories().add(cat3);
		p5.getCategories().add(cat2);
		//Temos uma correspondencia entre o modelo orientado a objetos e o modelo relacional.
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));//Salva no banco.
		
		
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456"); //instanciado esses 2 usuários
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456"); //Estamos colocando null, porque o id vai ser gerado pelo banco de dados.
		
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1); 
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, u2); 
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, u1);
		
		userRepository.saveAll(Arrays.asList(u1, u2)); //esse comando salva os dois usuários no banco de dados. Para salvar usamos o UserRepository, que é o objeto repository que acessa dos dados. Em saveAll, eu passo uma lista de objetos (Arrays.asList(passando objeto u1 e u2)) e ele salva essa lista no banco de dados.
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
		
		OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice()); 
		OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice()); 
		OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice()); 
		OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice());
		
		orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));
		
		Payment pay1 = new Payment(null, Instant.parse("2019-06-20T21:53:07Z"), o1);
		o1.setPayment(pay1);
		
		orderRepository.save(o1);		
		
	}	
}
