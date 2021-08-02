package sk.db.dbstore.dao;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import sk.db.dbstore.model.TradeBean;

/**
 * 
 * @author KashidSh
 *
 */
public interface DBTradeStoreDao {

	/**
	 * A hash table supporting full concurrency of retrievals and high expected
	 * concurrency for updates. This class obeys the same functional specification
	 * as Hashtable, and includes versions of methods corresponding to each method
	 * of Hashtable. 
	 * Type Parameters: 
	 * K - the type of keys maintained by this map 
	 * V - the type of mapped values
	 */
	public static Map<String, TradeBean> tradeStoreMap = new ConcurrentHashMap<>();

	
	List<TradeBean> findAll();

	TradeBean findTrade(String tradeId);	
	 
	
	public void addTrade(TradeBean tradeBean);
}