package br.com.yanaga.azure.javaee7.model;

import com.mysema.query.jpa.impl.JPAQuery;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ReceitaRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public List<Receita> findAll() {
		JPAQuery query = new JPAQuery(entityManager);
		QReceita qReceita = QReceita.receita;
		return query.from(qReceita).list(qReceita);
	}

	public List<Receita> findByNomeLike(String consulta) {
		JPAQuery query = new JPAQuery(entityManager);
		QReceita qReceita = QReceita.receita;
		return query.from(qReceita).where(qReceita.nome.containsIgnoreCase(consulta)).list(qReceita);
	}

	public Receita save(Receita receita) {
		System.out.println(receita.getId());
		return entityManager.merge(receita);
	}

	public void delete(Receita receita) {
		entityManager.remove(entityManager.merge(receita));
	}

}
