package com.sk.db;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;

import com.sk.db.dbstore.controller.DBStoreController;
import com.sk.db.dbstore.dao.DBTradeStoreDao;
import com.sk.db.dbstore.model.TradeBean;

/**
 * 
 * @author KashidSh
 *
 */
@ComponentScan({"sk.db.dbstore.service"})
@SpringBootTest
class DbstoreApplicationTests {

	@Autowired
	DBTradeStoreDao dbTradeStoreDao;
	
	@Autowired
	private DBStoreController dbStoreController;
	
    Date todaysDate=Calendar.getInstance().getTime();
    SimpleDateFormat sd=new SimpleDateFormat("dd/MM/yyyy");
	@Test
	void contextLoads() {
		
	}

	
	//Higher or equal Version Trade must be saved
	@Test
    @Order(1)
    void testVersionHigh() throws Exception
    {
		Date maturityDate=sd.parse("20/08/2021");
        TradeBean tradeBean1=new TradeBean("T2",2,"CP-2","B1",maturityDate, todaysDate, 'N');
        dbTradeStoreDao.addTrade(tradeBean1);
               

        //Changing Version as 5 and Changing Counter-Party ID to CP-4
       TradeBean tradeBean2=new TradeBean("T2",5,"CP-4","B1",maturityDate, todaysDate, 'N');
       dbTradeStoreDao.addTrade(tradeBean2);
        assertEquals("CP-4",dbTradeStoreDao.findTrade("T2").get().getCounterParty());
    }
	
	
	
	/*
	 * During transmission if the lower version is being received by the store it
	 * will reject the trade and throw an exception. If the version is same it will
	 * override the existing record.
	 */
	//Check if Version is low the trade will be rejected
    //T3	5	CP-3	B1	20/05/2014	today date	N
    //T2	1	CP-2	B1	20/05/2021	today date	N
    @Test
    @Order(2)
    void testVersionLow() throws Exception
    {
        Date maturityDate=sd.parse("20/08/2021");

        TradeBean tradeBean1=new TradeBean("T3",5,"CP-3","B1",maturityDate, todaysDate, 'N');
        dbTradeStoreDao.addTrade(tradeBean1);
       
        
        TradeBean tradeBean2=new TradeBean("T3",1,"CP-2","B1",maturityDate, todaysDate, 'N');

        assertThrows(Exception.class,()->dbTradeStoreDao.addTrade(tradeBean2),"1 is less than 5");

    }
    
    
    //Valid Trade must be saved
    @Test
	void testTradeValidateAndSave() throws ParseException {
    	
    	//T1 1 CP-1 B1 08/08/2021 &lt;today 	date&gt;    	N
    	Date maturityDate=sd.parse("20/08/2021");
    	TradeBean tradeBean=new TradeBean("T1",1,"CP-1","B1",maturityDate, todaysDate, 'N');
		ResponseEntity responseEntity = dbStoreController.tradeValidateStore(tradeBean);
		
		Assertions.assertEquals(ResponseEntity.status(HttpStatus.OK).build(),responseEntity);
		List<TradeBean> tradeList =dbStoreController.findAllTrades();
		Assertions.assertEquals(1, tradeList.size());
		Assertions.assertEquals("T1",tradeList.get(0).getTradeId());
	}
    
    //2. Store should not allow the trade which has less maturity date then today date.
    
	@Test
	void testLessMaturity() throws ParseException {

		try {
			Date maturityDate = sd.parse("01/08/2021");
			TradeBean tradeBean = new TradeBean("T1", 1, "CP-1", "B1", maturityDate, todaysDate, 'N');
			ResponseEntity responseEntity = dbStoreController.tradeValidateStore(tradeBean);
		} catch (DBStoreTradeException dbsexep) {
			Assertions.assertEquals("T1  Trade Id is not found", dbsexep.getMessage());
		}

	}
	
}
