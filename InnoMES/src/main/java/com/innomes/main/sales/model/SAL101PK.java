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
public class SAL101PK implements Serializable {
	private Integer salPlanNo;
	
	private Integer salPlanSeq;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SAL101PK other = (SAL101PK) obj;
		if (salPlanNo == null) {
			if (other.salPlanNo != null)
				return false;
		} else if (!salPlanNo.equals(other.salPlanNo))
			return false;
		if (salPlanSeq == null) {
			if (other.salPlanSeq != null)
				return false;
		} else if (!salPlanSeq.equals(other.salPlanSeq))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((salPlanNo == null) ? 0 : salPlanNo.hashCode());
		result = prime * result + ((salPlanSeq == null) ? 0 : salPlanSeq.hashCode());
		return result;
	}
	
	
}
