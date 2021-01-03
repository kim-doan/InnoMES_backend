package com.innomes.main.generator;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class ProductCodeGenerator implements IdentifierGenerator {

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		 
        String prefix = "P";
        Connection connection = session.connection();
        try {
 
            PreparedStatement ps = connection
                    .prepareStatement("SELECT NEXT VALUE FOR SEQ_PRODUCT_ID AS nextval");
 
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("nextval");
                String code = prefix + String.format("%05d", id);
                return code;
            }
 
        } catch (SQLException e) {
            throw new HibernateException(
                    "Unable to generate Stock Code Sequence");
        }
        return null;
	}
}
