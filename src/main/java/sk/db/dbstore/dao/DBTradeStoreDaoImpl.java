package sk.db.dbstore.dao;


import sk.db.dbstore.model.TradeBean;

import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 * @author KashidSh
 *
 */
@Repository
public class DBTradeStoreDaoImpl implements DBTradeStoreDao{
    
    /**
     * 
     */
	public void addTrade(TradeBean tradeStore) {
		tradeStore.setCreatedDate(new Date());
	}
	
   /**
    * 
    */
    public List<TradeBean> findAll() {
         return tradeStoreMap.values().stream().
                 collect(Collectors.toList());
    }

    /**
     * 
     */
    public TradeBean findTrade(String tradeId) {
        return tradeStoreMap.get(tradeId);
    }
}
 