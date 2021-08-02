package com.sk.db;

/**
 * 
 * @author KashidSh
 *
 */
public class DBStoreTradeException extends RuntimeException {

  
	private static final long serialVersionUID = -8081164045125398861L;
	private String id = "";
    
    public DBStoreTradeException(String id) {
		// TODO Auto-generated constructor stub
    	super(id);
    	this.id=id;
	}

 

    public String getId() {
        return id;
    }
}