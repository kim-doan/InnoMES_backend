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
public class MST220PK implements Serializable {
	private String prdtId;
	
	private String procCode;
	
	private Integer bomSeq;
	
	private Integer swapSeq;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MST220PK other = (MST220PK) obj;
		if (bomSeq == null) {
			if (other.bomSeq != null)
				return false;
		} else if (!bomSeq.equals(other.bomSeq))
			return false;
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
		if (swapSeq == null) {
			if (other.swapSeq != null)
				return false;
		} else if (!swapSeq.equals(other.swapSeq))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bomSeq == null) ? 0 : bomSeq.hashCode());
		result = prime * result + ((prdtId == null) ? 0 : prdtId.hashCode());
		result = prime * result + ((procCode == null) ? 0 : procCode.hashCode());
		result = prime * result + ((swapSeq == null) ? 0 : swapSeq.hashCode());
		return result;
	}
}
