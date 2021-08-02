package sk.db.dbstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sk.db.DBStoreTradeException;

import sk.db.dbstore.model.TradeBean;
import sk.db.dbstore.service.DBTradeStoreService;

/**
 * 
 * @author KashidSh
 *
 */
@RestController
public class DBStoreController {
	@Autowired
	DBTradeStoreService dbTradeStoreService;

	/*
	 */
	@PostMapping("/tradeStore")
	public ResponseEntity<String> tradeValidateStore(@RequestBody TradeBean trade) {
		if (dbTradeStoreService.isValidTrade(trade)) {
			dbTradeStoreService.addTrade(trade);
		} else {
			// return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
			throw new DBStoreTradeException(trade.getTradeId() + "  Trade Id is not found");
		}
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@GetMapping("/tradeStore")
	public List<TradeBean> findAllTrades() {
		return dbTradeStoreService.findAll();
	}

	
	 

}