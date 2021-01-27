package com.innomes.main.master.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class MST210PK implements Serializable{
	private String prdtId;
	
	private Integer routingRev;
	
	private String procCode;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MST210PK other = (MST210PK) obj;
		if (prdtId == null) {
			if (other.prdtId != null)
				return false;
		} else if (!prdtId.equals(other.prdtId))
			return false;
		if (procCode == null) {
			if (other.procCode != null)
				return false;
		} else if (!procCode.equals(other.procCode))
			return false;
		if (routingRev == null) {
			if (other.routingRev != null)
				return false;
		} else if (!routingRev.equals(other.routingRev))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((prdtId == null) ? 0 : prdtId.hashCode());
		result = prime * result + ((procCode == null) ? 0 : procCode.hashCode());
		result = prime * result + ((routingRev == null) ? 0 : routingRev.hashCode());
		return result;
	}
}
