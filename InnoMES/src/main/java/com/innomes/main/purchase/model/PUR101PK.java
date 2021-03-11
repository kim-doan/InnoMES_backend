package com.innomes.main.purchase.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PUR101PK implements Serializable {
	private String reqNo;
	
	private Integer reqSeq;
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PUR101PK other = (PUR101PK) obj;
		if (reqNo == null) {
			if (other.reqNo != null)
				return false;
		} else if (!reqNo.equals(other.reqNo))
			return false;
		if (reqSeq == null) {
			if (other.reqSeq != null)
				return false;
		} else if (!reqSeq.equals(other.reqSeq))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((reqNo == null) ? 0 : reqNo.hashCode());
		result = prime * result + ((reqSeq == null) ? 0 : reqSeq.hashCode());
		return result;
	}
}
