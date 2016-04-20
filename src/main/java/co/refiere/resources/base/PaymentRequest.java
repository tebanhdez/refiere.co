package co.refiere.resources.base;

import java.math.BigDecimal;

public class PaymentRequest {
    
    private int id;
    private int orderId;
    private int currencyId;
    private BigDecimal discount;
    private BigDecimal price;
    private String paymentDescription;
    private int paymentTypeId;
    private BigDecimal totalPrice;
    private String accountingTrackRef;
    private String userName;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    public int getCurrencyId() {
        return currencyId;
    }
    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
    }
    public BigDecimal getDiscount() {
        return discount;
    }
    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public String getPaymentDescription() {
        return paymentDescription;
    }
    public void setPaymentDescription(String paymentDescription) {
        this.paymentDescription = paymentDescription;
    }
    public int getPaymentTypeId() {
        return paymentTypeId;
    }
    public void setPaymentTypeId(int paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
    public String getAccountingTrackRef() {
        return accountingTrackRef;
    }
    public void setAccountingTrackRef(String accountingTrackRef) {
        this.accountingTrackRef = accountingTrackRef;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
}
