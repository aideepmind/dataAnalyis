package com.hejia.dataAnalysis.module.common.dao;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;

public class StringJsonUserType implements UserType {
	
	public static final int[] types = {Types.JAVA_OBJECT};
	
	public int[] sqlTypes() {
		return types;
	}

	public Class returnedClass() {
		 return String.class;
	}

	public boolean equals(Object x, Object y) throws HibernateException {
		if(x== null) {
            return y== null;
        }
        return x.equals( y);
	}

	public int hashCode(Object x) throws HibernateException {
		return x.hashCode();
	}

	public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner) throws HibernateException, SQLException {
		return rs.getString(names[0]);
	}

	public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session) throws HibernateException, SQLException {
		if (value == null) {
            st.setNull(index, Types.OTHER);
            return;
        }
        st.setObject(index, value, Types.OTHER);
	}

	public Object deepCopy(Object value) throws HibernateException {
		 return value;
	}

	public boolean isMutable() {
		return true;
	}

	public Serializable disassemble(Object value) throws HibernateException {
		return (String) this.deepCopy(value);
	}

	public Object assemble(Serializable cached, Object owner) throws HibernateException {
		return this.deepCopy(cached);
	}

	public Object replace(Object original, Object target, Object owner) throws HibernateException {
		return original;
	}

}
