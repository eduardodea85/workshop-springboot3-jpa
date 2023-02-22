package com.dea.course.entities;

//Criar a entidade User e o primeiro resource
//Resurce é o recurso web correspondente a entidade User. Esse recurso vai disponibilizar dois endpoints para recuperar os usuários cadastrados e também um usuário informando o ID dele.
/*
 User entity and resource - Essa classe User é uma entidade do nosso modelo de dominio.
Basic entity checklist: 
 Basic attributes
 Associations (instantiate collections)
 Constructors
 Getters & Setters (collections: only get)
 hashCode & equals por padrão colocar só Id, para comparar um objeto com outro.
 Serializable - É uma interface que define nos objetos, quando você quer que esses objetos possam ser transformados em cadeias de bytes. Serve para que o objeto trafegue na rede, que seja gravado em arquivos... Sempre coloca na frente do nome da classe o implements Serializable. Como é um Serializable tem que ter um número de série padrão acrescentando o 1L.
 */


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

//Criado a classe nesse commit
// Colocar nessa classe algumas anotations do JPA, para instruir para o JPA, como ele vai converter os objetos para o modelo relacional. 
@Entity //Esta anotation é a especificação do JPA
@Table(name = "tb_user") //Ao usar essa anotation, especificar o nome da tabela do banco de dados. 
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id //Essa anotion define a chave primária.
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Essa definição de estratégia do banco de dados vai funcionar no nosso banco de dados.
	private Long id; //Esta é a chave primária do banco de dados. Como ela é uma chave numerica, vai ser auto-incrementado no banco de dados.
	private String name;
	private String email;
	private String phone;
	private String password;
	
	@JsonIgnore
	@OneToMany(mappedBy = "client")
	private List<Order> orders = new ArrayList<>();
	
	public User() {
	}

	public User(Long id, String name, String email, String phone, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<Order> getOrders() {
		return orders;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	//Depois de finalizar essa classe, precisamos testar nosso Rest dessa aplicação Spring Boot e ver se está funcionando. Para isso tem que criar um recurso básico baseado na classe User. Criar uma nova classe no UserResource no pacote resoueces.
	
	
	
}
