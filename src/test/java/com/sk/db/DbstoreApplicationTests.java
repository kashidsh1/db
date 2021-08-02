package com.sk.db;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sk.db.dbstore.dao.DBTradeStoreDao;
import sk.db.dbstore.model.TradeBean;

/**
 * 
 * @author KashidSh
 *
 */
@SpringBootTest
class DbstoreApplicationTests {

	@Autowired
	DBTradeStoreDao dbTradeStoreDao;
	
    Date todaysDate=Calendar.getInstance().getTime();
    SimpleDateFormat sd=new SimpleDateFormat("dd/MM/yyyy");
	@Test
	void contextLoads() {
	}

	@Test
    @Order(1)
    void testVersionHigh() throws Exception
    {
		Date maturityDate=sd.parse("02/08/2021");
        TradeBean t2=new TradeBean("T2",2,"CP-2","B1",maturityDate, todaysDate, 'N');
        dbTradeStoreDao.addTrade(t2);
        
        

        //Changing Version as 3 and Changing Counter-Party ID to CP-4
       TradeBean t3=new TradeBean("T2",5,"CP-4","B1",maturityDate, todaysDate, 'N');
       dbTradeStoreDao.addTrade(t3);
        assertEquals("CP-4",dbTradeStoreDao.findTrade("T2"));
    }
	
	
	//Check if Version is low the trade will be rejected
    //T3	5	CP-3	B1	20/05/2014	today date	N
    //T2	1	CP-2	B1	20/05/2021	today date	N
    @Test
    @Order(2)
    void testVersionLow() throws Exception
    {
        Date maturityDate=sd.parse("20/05/2014");

        TradeBean t5=new TradeBean("T3",5,"CP-3","B1",maturityDate, todaysDate, 'N');
        dbTradeStoreDao.addTrade(t5);
        int sizeofList=dbTradeStoreDao.findAll().size();
        
        TradeBean tradeBean=new TradeBean("T3",1,"CP-2","B1",maturityDate, todaysDate, 'N');

        assertThrows(Exception.class,()->dbTradeStoreDao.addTrade(tradeBean),"1 is less than 5");

    }
	
}
