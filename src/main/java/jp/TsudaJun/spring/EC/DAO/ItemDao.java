package jp.TsudaJun.spring.EC.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jp.TsudaJun.spring.EC.model.Item;
import jp.TsudaJun.spring.EC.model.ItemAttribute;
import jp.TsudaJun.spring.EC.model.User;

@Repository
@Transactional
public class ItemDao {
	
	@PersistenceContext
	EntityManager manager;
	
	public void close() {
		manager.clear();
	}
	
	@Transactional
	public void persist(Item item) {
		manager.persist(item);
	}
	
	@Transactional
	public void merge(Item item) {
		manager.merge(item);
	}
	
	public List<Item> getAllItems(){
		List<Item> ret = manager.createNativeQuery("select * from item", Item.class).getResultList();
		return ret;
	}
	
	public Item getItemById(int itemid) {
		
		Item ret = null;
		try {
			ret = (Item) manager.createNativeQuery("select * from item where itemid = :id", Item.class)
					.setParameter("id", itemid)
					.getSingleResult();
		}catch(Exception e) {
			
		}
		
		return ret;
	}
	
	public List<Item> getItemsByAttributeno(int attributeno){
		List<Item> ret = manager.createNativeQuery("select * from item where itemattribute = :no", Item.class)
									.setParameter("no", attributeno)
									.getResultList();
		
		return ret;

	}
	
	public List<Item> getItemsByWordAndAttribute(String word, int attributeno){
		
		List<Item> ret = null;
		try {
			ret = manager.createNativeQuery("select * from item where itemname LIKE :word AND itemattribute = :no"
					,Item.class)
					.setParameter("word", "%"+word+"%")
					.setParameter("no", attributeno)
					.getResultList();
		}catch(Exception e) {
			
		}
		
		return ret;
	}
	
	public List<Item> getItemsByWord(String word){
		
		List<Item> ret = null;
		try {
			ret = manager.createNativeQuery("select * from item where itemname LIKE :word"
					,Item.class)
					.setParameter("word", "%"+word+"%")
					.getResultList();
		}catch(Exception e) {
			
		}
		
		return ret;
	}

}
