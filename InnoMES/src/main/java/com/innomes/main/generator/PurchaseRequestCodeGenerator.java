package com.innomes.main.generator;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class PurchaseRequestCodeGenerator implements IdentifierGenerator {

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd"); 
		String prefix = "RQ" + dateFormat.format(new Date());
		Connection connection = session.connection();
		
		try {
			PreparedStatement ps = connection
					.prepareStatement("SELECT NEXT VALUE FOR SEQ_PUR_REQUEST_NO AS nextval");
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				int id = rs.getInt("nextval");
				return prefix + String.format("%04d", id);
			}
		} catch (SQLException e) {
			throw new HibernateException("Unable to generate Stock Code Sequence");
		}
		
		return null;
	}
	
}
