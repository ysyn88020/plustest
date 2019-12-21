package cn.flw.plustest.utils;
import com.alibaba.fastjson.JSON;

/**
 * <p>
 * BeanJsonUtil实体与Json转换类
 * </p>
 *
 * @author f_liwen
 * @since 2019-12-21
 */
public class BeanJsonUtil {

	/**
	 * 将对象转为json字符串。
	 * @param object 要转换的对象
	 * @return 返回json串
	 */
	public static String object2jstr(Object object) {
		String js = JSON.toJSONString(object);
		return js;
	}

	/**
	 * 将json串转换为指定的对象。
	 * @param jstr json串
	 * @param c 目标对象类型
	 * @return 返回目标对象
	 */
	@SuppressWarnings("unchecked")
	public static  <T> T jstr2object(String jstr, Class c) {
		T o = (T)JSON.parseObject(jstr,c);
		return o;
	}
	
}
