package com.sk.db.dbstore.model;
 
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
@Entity
@Table(name = "Trades")
public class TradeBean {

	public TradeBean(){
		   // Add here init stuff if needed
		}
    @Id
    private String tradeId;

    private int version;

    private String counterParty;

    private String bookId;

    private Date maturityDate;

    private Date createdDate;

    private char expiredFlag;

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getCounterParty() {
        return counterParty;
    }

    public void setCounterParty(String counterParty) {
        this.counterParty = counterParty;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public Date getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(Date maturityDate) {
        this.maturityDate = maturityDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public char getExpiredFlag() {
        return expiredFlag;
    }

    public void setExpiredFlag(char expiredFlag) {
        this.expiredFlag = expiredFlag;
    }

    /**
	 * 
	 * @param tid
	 * @param ver
	 * @param cpid
	 * @param bkid
	 * @param mdate
	 * @param cdate
	 * @param exp
	 */
	public TradeBean(String tid,int ver,String cpid,String bkid,Date mdate,Date cdate,char exp)
	{
		tradeId=tid;
		version=ver;
		counterParty=cpid;
		bookId=bkid;
		maturityDate=mdate;
		createdDate=cdate;
		expiredFlag=exp;
		
	}
	
    
    @Override
    public String toString() {
        return "Trade{" +
                "tradeId='" + tradeId + '\'' +
                ", version=" + version +
                ", counterParty='" + counterParty + '\'' +
                ", bookId='" + bookId + '\'' +
                ", maturityDate=" + maturityDate +
                ", createdDate=" + createdDate +
                ", expiredFlag='" + expiredFlag + '\'' +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {

      if (this == o)
        return true;
      if (!(o instanceof TradeBean))
        return false;
      TradeBean tradeBean = (TradeBean) o;
      return Objects.equals(this.tradeId, tradeBean.tradeId) && Objects.equals(this.bookId, tradeBean.bookId);
    }

    @Override
    public int hashCode() {
      return Objects.hash(this.tradeId, this.bookId);
    }

    
    
}