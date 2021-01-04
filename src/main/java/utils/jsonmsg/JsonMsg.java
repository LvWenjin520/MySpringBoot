package utils.jsonmsg;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回到前端的消息
 * @author LvWenJin
 *
 */
public class JsonMsg {
	
	/**
	 * 返回值
	 * @param msg 要带给前端的消息
	 * @return
	 */
	public static Map<String,String> success(String msg) {
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("flag", "success");
		map.put("msg", msg);
		return map;
	}
	
	//失败
	public static Map<String,String> faild(String msg) {
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("flag", "faild");
		map.put("msg", msg);
		return map;
	}
	
}
