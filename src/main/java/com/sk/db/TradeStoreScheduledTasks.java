package com.sk.db;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sk.db.dbstore.service.DBTradeStoreService;

/**
 * 
 * @author KashidSh
 *
 */
@ComponentScan(basePackages = {"sk.db.dbstore.service"})
@Component
public class TradeStoreScheduledTasks {

	private static final Logger log = LoggerFactory.getLogger(TradeStoreScheduledTasks.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	
	@Autowired
	DBTradeStoreService dbTradeStoreService;

	@Scheduled(fixedRate = 5000)
	public void reportCurrentTime() {
		log.info("The time is now {}", dateFormat.format(new Date()));
		dbTradeStoreService.updateExpiryTradeDate();
		
		
		
	}
}