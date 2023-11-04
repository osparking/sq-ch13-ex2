package sq_ch13_ex2.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Account {
	private int id;
	private String name;
	private BigDecimal amount;

}
