package com.sk.db.dbstore.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sk.db.DBStoreTradeException;
import com.sk.db.dbstore.dao.DBTradeStoreDao;
import com.sk.db.dbstore.model.TradeBean;


/**
 * 
 * @author KashidSh
 *
 */
@RestController
public class DBStoreController {
	@Autowired
	DBTradeStoreDao dbTradeStoreDao;

	/*
	 */
	@PostMapping("/tradeStore")
	public ResponseEntity<String> tradeValidateStore(@RequestBody TradeBean trade) throws DBStoreTradeException{
		if (dbTradeStoreDao.isValidTrade(trade)) {
			dbTradeStoreDao.addTrade(trade);
		} else {
			// return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
			throw new DBStoreTradeException(trade.getTradeId() + "  Trade Id is not found");
		}
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@GetMapping("/tradeStore")
	public List<TradeBean> findAllTrades() {
		return dbTradeStoreDao.findAll();
	}


	@GetMapping("/findTrade/{tradeID}")
	public Optional<TradeBean> findTrade(@PathVariable String  tradeID) {
		return dbTradeStoreDao.findTrade(tradeID);
	}
	 

}