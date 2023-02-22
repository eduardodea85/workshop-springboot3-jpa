package com.dea.course.entities;

import java.util.HashSet;
import java.util.Set;

public class Product {

	private Long id;
	private String name;
	private String description;
	private Double price;
	private String imgUrl;
	
	private Set<Category> categories = new HashSet<>(); //Não vou usar uma lista List. Usar uma outra coleção do java que é o Set. Porque o Set representa um conjunto, isso é para garantir que eu não vou ter o produto com mais de uma ocorrencia da mesma categoria. O mesmo produto não pode ter uma mesma categoria mais de uma vez.
	//Instanciamos para que a a minha coleção não comece valendo nula. Ela tem que começar vazia, porém instanciada.
	
}


