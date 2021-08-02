package com.sk.db;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * @author KashidSh
 *
 */
@ControllerAdvice
class TradeRejectAdvice {

  @ResponseBody
  @ExceptionHandler(DBStoreTradeException.class)
  @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
  String tradeNOTAceeptedHandler(DBStoreTradeException ex) {
    return ex.getMessage();
  }
}