package com.sk.db.dbstore.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.sk.db.DBStoreTradeException;
import com.sk.db.dbstore.dao.DBStoreRepo;
import com.sk.db.dbstore.dao.DBTradeStoreDao;
import com.sk.db.dbstore.model.TradeBean;
 

/**
 * 
 * @author KashidSh
 *
 */
 
@Service
@ComponentScan(basePackages = {"sk.db.dbstore.dao"})
public class DBTradeStoreService {

	private static final Logger log = LoggerFactory.getLogger(DBTradeStoreService.class);

	@Autowired
	DBTradeStoreDao dbTradeStoreDao;
	@Autowired
	private DBStoreRepo dbStoreRepo;

	/*
	 * During transmission if the lower version is being received by the store it
	 * will reject the trade and throw an exception. If the version is same it will
	 * override the existing record.
	 */

	private boolean versionValidation(TradeBean tradeBean, TradeBean oldTradeBean) {

		if (tradeBean.getVersion() >= oldTradeBean.getVersion()) {
			return true;
		}
		return false;
	}

	/*
	 * Validation code
	 */
	public boolean isValidTrade(TradeBean tradeBean) {
		if (validateMaturityDate(tradeBean)) {

			Optional<TradeBean> tardePresent = dbStoreRepo.findById(tradeBean.getTradeId());
			if (tardePresent.isPresent()) {
				return versionValidation(tradeBean, tardePresent.get());
			} else {
				return true;
			}
		}
		return false;
	}

	// Store should automatically update the expire flag if in a store the trade
	// crosses the maturity
	// date.
	public void updateExpiryTradeDate() {
		// Not yet completed and not working , but I believe this should be one approach
		// should be used.

		dbStoreRepo.findAll().stream().forEach(e -> setExpiryDateAndSave(e)

		);

	}

	public void setExpiryDateAndSave(TradeBean bean) {

		if (!validateMaturityDate(bean)) {
			bean.setExpiredFlag('N');
			dbStoreRepo.save(bean);

		}
	}

	// Store should not allow the trade which has less maturity date then today
	// date.
	private boolean validateMaturityDate(TradeBean tradeBean) {

		Date maturityDate = tradeBean.getMaturityDate();
		LocalDate date = maturityDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		return date.isBefore(LocalDate.now()) ? false : true;
	}

	/**
	 * 
	 * @param tradeBean
	 */
	public void addTrade(TradeBean tradeBean) throws DBStoreTradeException {

		tradeBean.setCreatedDate(new Date());

		if (isValidTrade(tradeBean)) {

			dbStoreRepo.save(tradeBean);
		} else {
			throw new DBStoreTradeException(tradeBean.getTradeId());
		}

	}

	public List<TradeBean> findAll() {
		return dbStoreRepo.findAll();

	}
	
	public  Optional<TradeBean> find(String tradeID) {
		return dbStoreRepo.findById(tradeID);

	}

}