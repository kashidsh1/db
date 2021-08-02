package com.sk.db.dbstore.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import com.sk.db.dbstore.model.TradeBean;

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

	 Optional<TradeBean> findTrade(String tradeId);	
	 
	 boolean isValidTrade(TradeBean trade);
	
	public void addTrade(TradeBean tradeBean);
}