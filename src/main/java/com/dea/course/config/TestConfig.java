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
import com.dea.course.entities.User;
import com.dea.course.entities.enums.OrderStatus;
import com.dea.course.repositories.CategoryRepository;
import com.dea.course.repositories.OrderRepository;
import com.dea.course.repositories.UserRepository;

@Configuration //Para falar para o spring que essa é uma classe especifica de configuração, colocamos essa anotation.
@Profile("test") //Para falar que essa classe é uma configuração especifica para o perfil de teste, coloco essa anotation com o mesmo nome que colocamos no profile de application.properties. Aí o spring consegue identificar que ele vai rodar essa configuração, somente quando estiver no perfil de test.
public class TestConfig implements CommandLineRunner{ //Essa classe de configuração vai servir para a gente fazer o nosso database seeding, ou seja, para popular o nosso banco de dados com alguns objetos. No caso os objetos abaixo u1 e u2. Mas para eu popular o banco de dados, vou precisar salvar os dados. Para salvar os dados, a classe que faz isso é o repository. Nesse momento, vou ter o meu primeiro caso de injeção de dependencia, porque essa classe de TestConfig vai precisar ter uma dependencia para o meu UserReposiry. E na POO aprendemos que quando um serviço depende de outro, essa dependencia precisa ser desaclopada. Então essa injeção de dependencia desaclopada ela pode ser feita manualmente por meio de um construtor, ou método set... Mas quando utilizamos um framework, geralmente tem um mecanismo de injeção de dependencia automático.
	
	@Autowired //Só com essa anotação, o próprio spring na hora que tiver rodando a aplicação, vai resolver a dependência e associar ima instancia de userRepository.
	private UserRepository userRepository; //Para fazer um objeto depender de outro precisa declarar a dependecia com um atributo e para que o spring consiga resolver essa dependecia e associar uma instancia do userRepository no meu TestConfig, basta colocar o anotation @Autowired
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public void run(String... args) throws Exception {
		
		Category cat1 = new Category(null, "Electronics"); 
		Category cat2 = new Category(null, "Books"); 
		Category cat3 = new Category(null, "Computers");
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456"); 
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1); 
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, u2); 
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, u1);
		
		userRepository.saveAll(Arrays.asList(u1, u2));		
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
		
	}
	
	
	
	
}
