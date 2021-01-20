package com.innomes.main.generator;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;

public class SalesPlanCodeGenerator {

	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		 
        Connection connection = session.connection();
        try {
 
            PreparedStatement ps = connection
                    .prepareStatement("SELECT NEXT VALUE FOR SEQ_SAL_PLAN_NO AS nextval");
 
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("nextval");
                return id;
            }
 
        } catch (SQLException e) {
            throw new HibernateException(
                    "Unable to generate Stock Code Sequence");
        }
        return null;
	}
}
