package com.dea.course.resources;

import java.net.URI;

//Implementar um recurso básico na aplicação Spring Boot, lembrando que estamos implementando a arquitetura básica tendo uma camada de recursos que vão ser os controladores Rest Controllers, esses controladores Rest vão depender de uma camada de serviços (Service Layer), e por sua vez vai depender de uma camada de acesso a dados que será os Data Access Layer (data repositories). Vamos assim iniciar a nossa camada de recursos que é essa primeira classe UserResource.

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dea.course.entities.User;
import com.dea.course.services.UserService;

import jakarta.servlet.Servlet;

@RestController //Para falarmos que essa classe é um recurso web que é implementado por um controlador Rest colocamos essa anotation.
@RequestMapping(value = "/users") //Essa anotatio dá um nome ao recurso. Como é uma classe de Usuário que está sendo usado numa entidade User(UserResource), coloco o nome de /users.
public class UserResource {

	//Abaixo criar um método que vai ser um endpoint para acessar os usuários.
	@Autowired//anotação para o spring fazer a injeção de dependencia
	private UserService service;//dependencia para o ServiceUser. Para ela funcioanar, precisa estar registrada como componente do spring. Para isso preciso ir na classe UserService e registra-la como componente. Fazemos isso usando uma anotation no começo da classe UserService como @Service.
	
	@GetMapping //Para indicar que esse método responde a requisição do tipo get do http eu coloco essa anotation.
	public ResponseEntity<List<User>> findAll() { //Tipo de retorno desse método é o ResponseEntity, que é um tipo especifico do String Boot para retornar resposta de requisições web. Esse ResponseEntity é genérico. Ele espera um tipo e o tipo de resposta vai ser User, da classe User.
		List<User> list = service.findAll(); //findAll, método que vai retornar os meus usuários.
		return ResponseEntity.ok().body(list); //retornar o ResponseEntity.ok para retornar resposta com sucesso no http e .body chama o body para retornar o corpo da resposta desse meu usuário u que eu acabei de instanciar.
	}
	
	@GetMapping(value = "/{id}")//esse método indica que a minha requisição vai aceitar um id dentro da url
	public ResponseEntity<User> findById(@PathVariable Long id) {
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj);//Esse é nosso endpoint
	}
	
	@PostMapping
	public ResponseEntity<User> insert(@RequestBody User obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
		
}
