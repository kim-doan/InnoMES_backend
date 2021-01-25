package com.innomes.main.sales.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SAL111PK implements Serializable {
	private Integer ordId;
	private Integer ordSeq;
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SAL111PK other = (SAL111PK) obj;
		if (ordId == null) {
			if (other.ordId != null)
				return false;
		} else if (!ordId.equals(other.ordId))
			return false;
		if (ordSeq == null) {
			if (other.ordSeq != null)
				return false;
		} else if (!ordSeq.equals(other.ordSeq))
			return false;
		return true;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ordId == null) ? 0 : ordId.hashCode());
		result = prime * result + ((ordSeq == null) ? 0 : ordSeq.hashCode());
		return result;
	}
}
