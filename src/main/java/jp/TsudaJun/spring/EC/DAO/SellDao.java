package jp.TsudaJun.spring.EC.DAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import jp.TsudaJun.spring.EC.model.Sell;

@Repository
@Transactional
public class SellDao {
	
	@PersistenceContext
	EntityManager manager;
	
	@Transactional
	public void persist(Sell sell) {
		manager.persist(sell);
	}

}
