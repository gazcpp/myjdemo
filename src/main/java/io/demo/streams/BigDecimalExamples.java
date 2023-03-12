package io.demo.streams;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BigDecimalExamples {

	
	public void sumInvoicesStreamRecuce() {
		List<BigDecimal> invoices = new LinkedList<>();
		invoices.add(BigDecimal.valueOf(9.9));
		invoices.add(BigDecimal.valueOf(1.0));
		invoices.add(BigDecimal.valueOf(19.99));
		invoices.add(BigDecimal.valueOf(0.2));
		invoices.add(BigDecimal.valueOf(5.5));
		
		BigDecimal total1 = invoices.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
		System.out.println("Stream reduce Total " + total1.doubleValue());
		
		
	}
	
	public void sumInvoicesMapReduce() {
		
		List<Invoice> invoices = Arrays.asList(
                new Invoice("I1001", BigDecimal.valueOf(9.99), BigDecimal.valueOf(1)),
                new Invoice("I1002", BigDecimal.valueOf(19.99), BigDecimal.valueOf(1.5)),
                new Invoice("I1003", BigDecimal.valueOf(4.888), BigDecimal.valueOf(2)),
                new Invoice("I1004", BigDecimal.valueOf(4.99), BigDecimal.valueOf(5)),
                new Invoice("I1005", BigDecimal.valueOf(.5), BigDecimal.valueOf(2.3))
        );
		
		BigDecimal total2 = invoices.stream()
				.map(a -> a.getPrice().multiply(a.getQty()))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		
		System.out.println("map reduce Total " + total2.doubleValue());	
		
	}
	
	
	public static void main(String[] args) {
		BigDecimalExamples bd = new BigDecimalExamples();
		
		bd.sumInvoicesStreamRecuce();
		bd.sumInvoicesMapReduce();
	}
}

class Invoice{
	
	private String invoiceNo;
	private BigDecimal price;
	private BigDecimal qty;
	
	
	public Invoice(String invoiceNo, BigDecimal price, BigDecimal qty) {
		super();
		this.invoiceNo = invoiceNo;
		this.price = price;
		this.qty = qty;
	}
	
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getQty() {
		return qty;
	}
	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}
	
	
}
