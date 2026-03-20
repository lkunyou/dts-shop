package com.qiguliuxing.dts.admin.vo;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MonthSalesReportVo {

    private BigDecimal totalAmount;
    private Integer totalQuantity;
    private LocalDate orderDate;

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }
}
