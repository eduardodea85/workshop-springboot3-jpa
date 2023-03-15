package com.dea.course.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;


@Entity
@Table(name = "tb_product")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	private Double price;
	private String imgUrl;
	
	//Instanciando a Categoria
	//@Transient estava usando para o JPA não reconhecer a coleção. Vamos fazer então um mapeamento para transformar essas coleções que tem nas duas classes na tabela de associações que tem no modelo relacional.
	@ManyToMany
	@JoinTable(name = "tb_product_category",
	joinColumns = @JoinColumn(name = "product_id"),//JoinColumn do produto.
	inverseJoinColumns = @JoinColumn(name = "category_id"))//InverseJoinColumns é para definir qual é a classe estrangeira da oura entidade
	//Estou criando a tabela de associação, tb_product_category e tenho que informar qual vai ser o nome da chave estrangeira referente a tabela de produto. Só que na tabela de associação do banco de dados, aprendemos que ela vai ter a chave estrangeira das duas tabelas (no caso é produto e categoria), então também vou precisar definir isso como fiz acima.
	private Set<Category> categories = new HashSet<>(); //Não vou usar uma lista List. Usar uma outra coleção do java que é o Set. Porque o Set representa um conjunto, isso é para garantir que eu não vou ter o produto com mais de uma ocorrencia da mesma categoria. O mesmo produto não pode ter uma mesma categoria mais de uma vez.
	//Instanciamos para que a minha coleção não comece valendo nula. Ela tem que começar vazia, porém instanciada. O Set é uma interface e não pode ser instanciado, por isso uso uma classe correspondente a essa interface que é HashSet. Do mesmo jeito que usamos o List junto com ArrayList, usamos o Set junto com HashSet.
	
	@OneToMany(mappedBy = "id.product") //anotation para mapear um para muitos. id está na classe OrderItem e .product tem que ser igual ao nome que está em OrderItemPK. Assim declaramos a coleção de items nessa classe Product.
	private Set<OrderItem> items = new HashSet<>(); //Estou colocando Set e não List, para informar para o meu JPA que não vou aceitar repetição do mesmo item. 
	
	//Para finalizar esse mapeamento vou precisar criar o get, que vai reponder a uma lisar de orders conforme diagrama.
	
	public Product() {//Não coloco no construtor a coleção de Categories porque já estou intanciando ela acima.	
	}
	public Product(Long id, String name, String description, Double price, String imgUrl) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public Set<Category> getCategories() {
		return categories;
	}
	
	@JsonIgnore
	public Set<Order> getOrders() { //esse nome Orders está projetado no diagrama.
		Set<Order> set = new HashSet<>(); 
		for (OrderItem x : items) { //Vou percorrer com for cada objeto do tipo OrderItem, dando o apelido de x  e para cada obejto x contido na lista de items, vou adicionar no meu Set o x.getOrder()
			set.add(x.getOrder());
		}
		return set; //retorno o meu set.
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
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
}


