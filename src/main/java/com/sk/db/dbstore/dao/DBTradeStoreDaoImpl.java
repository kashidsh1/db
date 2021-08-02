package com.sk.db.dbstore.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import com.sk.db.DBStoreTradeException;
import com.sk.db.dbstore.model.TradeBean;
import com.sk.db.dbstore.service.DBTradeStoreService;

import java.util.Date;
import java.util.List;
import java.util.Optional;
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
	
	@Autowired
	DBTradeStoreService dbTradeStoreService;
	public void addTrade(TradeBean tradeStore) throws DBStoreTradeException{
		 
		dbTradeStoreService.addTrade(tradeStore);
	}
	
   /**
    * 
    */
    public List<TradeBean> findAll() {
         return dbTradeStoreService.findAll();
    }

    /**
     * 
     */
    public Optional<TradeBean> findTrade(String tradeId) {
        return dbTradeStoreService.find(tradeId);
    }
    
    
    public boolean isValidTrade(TradeBean trade){
    	return dbTradeStoreService.isValidTrade(trade);
    }
    
}
 