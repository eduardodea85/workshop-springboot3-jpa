package com.dea.course.entities.enums;

public enum OrderStatus {

	//Atribuir manualmente um valor númerico para cada tipo enumerado. Facilita na hora de fazer a manutenção da aplicação.
	WAITING_PAYMENT(1),
	PAID(2),
	SHIPPED(3),
	DELIVERED(4),
	CANCELED(5);
	
	//Quando colocamos um valor nos tipos enumerados, o java pede para fazer um programa para entender esse tipo enumerado, conforme abaixo.
	private int code;
	
	private OrderStatus(int code) {//Construtor do tipo enumerado. OrderStatus recebendo code sendo um número inteiro.
		this.code = code;
	}
	
	public int getCode() {//Esse método vai retornar o code
		return code;
	}
	
	public static OrderStatus valueOf(int code) {//método estatico para converter um valor numérico para o tipo enumerado. Cria o método estático porque não vai precisar instanciar. Esse método retorna um objeto do tipo OrderStatus chamando ele de valueOf, passando o code como argumento. Vou passar o code e esse método vai retornar o OrderStatus correspondente a esse código. Se passar por exemplo o código 1, ele retorna o correspondente.
		for (OrderStatus value : OrderStatus.values()) {//Percorre todos os possiveis valores do OrderStatus
			if (value.getCode() == code) {//Para cada um deles eu testo para ver se o code é o código correspondente e se for retorna o código.
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid OrderStatus code");//exeção do java Illegal que retorna a mensagem que o código não existe.
	}
	
}
