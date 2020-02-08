package kr.or.ddit.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SimpleDataMapper {
	public static List queryForList(ResultSet rs, Class resultClass) throws SQLException {
		List list = new ArrayList();
		while(rs.next()) {
			list.add( queryForObject(rs, resultClass) );
		}
		return list;
	}
	
	public static Object queryForObject(ResultSet rs, Class resultClass) throws SQLException {
//		member.setMem_id(rs.getString("MEM_ID"));
		try {
			Object result = resultClass.newInstance();
			Field[] fields = resultClass.getDeclaredFields();
			for(Field tmp : fields) {
				String propName = tmp.getName();
				PropertyDescriptor pd = 
						new PropertyDescriptor(propName, resultClass);
				Method setter = pd.getWriteMethod();
				Object value = null;
				try {
					if(tmp.getType().equals(String.class)) {
						value = rs.getString(propName);
					}else if(tmp.getType().equals(Integer.class)) {
						value = rs.getInt(propName);
					}
				}catch (Exception e) {
					continue;
				}
				
				setter.invoke(result, value);
			}
			return result;
		}catch (Exception e) {
			throw new SQLException(e);
		}
	}
}
