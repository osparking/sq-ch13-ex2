package sq_ch13_ex2.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class TransferRequest {
	private int rcvrId;
	private int sndrId;
	private BigDecimal amount;
}
