package com.dea.course.repositories;

//Implantando nosso primeiro repository, utilizando JPA Repository, do spring data jpa que é um sub-framework do eco sistema spring. Também vamos trabalhar com injeção de dependência automática feita pelo container do framework e também faremos nossa primeira instanciação do banco de dados com database seeding. Vamos inserir alguns dados automaticamente no banco de dados.

//Vamos começar criando no nosso JPA Repository. Lembrando que o Repository é a camada mais abaixo da nossa arquitetura. Temos o Resource que é o controlador rest, o contralador depende do serviço e o serviço depende repository. Vamos começar por baixo implementando o User Repository

//O User Repository, vai ser o responsável por fazer operações com a entidade User. Para criar o UserRepository reutilizando o JPA Repository, basta estender o JPARepository passando o tipo da entidade que você vai acessar(User), e o tipo da chave dessa entidade(Long).

import org.springframework.data.jpa.repository.JpaRepository;

import com.dea.course.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
}

//Apenas isso vai ser capaz de instanciar um objeto repository que vai ter varias operações para você trabalhar como usuário.

//Se criamos uma interface, vamos precisar criar a implementação dessa interface? Nesse caso especifico não, porque o spring data jpa já tem uma implementação padrão para essa interface. Se definirmos o JPARepository generic utilizando a sua entidade e o tipo do Id da entidade, já vai ter uma implementação padrão para este tipo especifico que definir.