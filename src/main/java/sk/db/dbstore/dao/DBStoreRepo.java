package sk.db.dbstore.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import sk.db.dbstore.model.TradeBean;

/**
 * 
 * @author KashidSh
 *
 */
public interface DBStoreRepo extends JpaRepository<TradeBean, String> {

}
