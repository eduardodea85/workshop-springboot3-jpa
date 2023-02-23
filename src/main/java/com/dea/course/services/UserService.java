package com.dea.course.services;

//Service layer, component registration
//Camada de serviço: A nossa arquitetura básica, tem a seguinte divisão de camadas lógicas. Aplication -> Resource Layer (Rest Controllers) -> Service Layer -> Data Access Layer (data repositories). Controlador que depende do serviço, que por sua vez depende do repository. Porque a gente faz a camada de serviço e não faz o controlador acessar diretamente o repository. Se faz uma camada intermediaria de serviço para implementar as regras de negócios, alguma orquestração de negócio que pode ser necessário (Ex. Ao Salvar um pedido, posso querer verificar o estoque se tem disponivel algum produto, salvar os itens para depois salvar o pedido), então são varias operações que se colocarmos tudo no contralador, apenas chamando as opções de salvamento lá do repository iria ficar um controlador muito carregado com regras de negócio. Então para separar as responsábilidades, fazendo com que o controlador faça apenas o que ele deve fazer que é o meio de campo entre as interações do usuário na aplicação e as regras de negócio, a ideia é construir um controlador enxuto e as regras de negócio vem para a camada de serviço.

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dea.course.entities.User;
import com.dea.course.repositories.UserRepository;

//Implementando

@Service //Essa anotation registra a classe como um serviço do spring e ela vai poder ser injetada automaticamente com o @Autowired
public class UserService {
	
	@Autowired //anotation para o spring fazer a injeção de dependencia de forma transparente ao programador
	private UserRepository repository; //declaro a dependencia para o UserRepository
	
	public List<User> findAll() { //método para buscar todos os usuários. Lista do tipo User e nome do método de findAll. Fizemos uma operação na camada de serviço onde ela repassa a chamada para o repository.findAll(), abaixo.
		return repository.findAll(); //retorna o repository com a operação findAll do método.
	}//Lá no meu UserResource que é o controlador Rest, preciso atualizar a implementação do findAll(), declarando uma lista de Usuário e chamando o método findAll().
	
	public User findById(Long id) { //operação para buscar os usuários por Id 
		Optional<User> obj = repository.findById(id); //chamo o método repository.findById retornando o Optinona<User>
		return obj.get(); //A operação get() do optional vai retornar o objeto do tipo <User> que estiver dentro do optional
	}
	
	
	
	
}