package jp.TsudaJun.spring.EC.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import jp.TsudaJun.spring.EC.model.Item;
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
	
	public void close() {
		if (manager.isOpen()) {
            manager.close();
        }
	}
	
	public List<Sell> getSellsByUserid(String userid){
		
		List<Sell> ret = null;
		try {
			ret = manager.createNativeQuery("select * from sell where userid = :userid order by boughtymd desc"
					,Sell.class)
					.setParameter("userid", userid)
					.getResultList();
		}catch(Exception e) {
			
		}
		
		return ret;
	}

}
