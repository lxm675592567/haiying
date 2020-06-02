package haiying;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import haiying.service.auth.base.AppSetting;
import haiying.service.auth.base.WxEndpoint;
import haiying.service.auth.domain.User;
import haiying.service.auth.service.UserService;
import haiying.service.auth.user.SessionKey;
import haiying.service.auth.user.Users;
import haiying.util.*;
import jdk.internal.org.objectweb.asm.tree.MultiANewArrayInsnNode;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.assertNotNull;

/**
 * @borball on 11/7/2016.
 */
public class UsersTest {

	private final UserService UserService;

	public UsersTest(UserService UserService) { // <3>
		this.UserService = UserService;
	}


	@Test
	@Ignore
	public void testCode2Session() {
//		SessionKey sessionKey = Users.with(new AppSetting("appid", "screctkey"))
//				.code2Session("011RedXY1NegvX0eLWXY1vVfXY1RedXu");
//		assertNotNull(sessionKey);
//		String code ="1213";
//		String url = WxEndpoint.get("url.user.code2session")+code;
//		System.out.println(url);
//		User user = new User();
//		user.setLoginType(1);
//		User user = UserService.findByOpenId("openId");
//		int loginType = user.getLoginType();
//		if(!Objects.isNull(loginType)){
//			//如果没有,注册,并返回一个注册标识给前端
//			System.out.println("111");
//		}
        String time = "2018年3月22日";
		time.replace("年","-");
		System.out.println(time);
	}

	public static void main(String[] args) throws Exception {
//		User user = new User();
//		user.setLoginType(1);
//		int loginType = user.getLoginType();
//		if(!Objects.isNull(loginType)){
//			//如果没有,注册,并返回一个注册标识给前端
//			System.out.println("111");
//		}
//		String time = "2018年3月22日";
//		String years = time.replaceAll("(?:年|月)", "-");
//		String data = years.replace("日", "");
//		System.out.println(data);

//		Date date = new Date();
//		System.out.println(date);
//		DateFormat df1 = DateFormat.getDateInstance();//日期格式，精确到日
//		System.out.println(df1.format(date));
//		DateFormat df2 = DateFormat.getDateTimeInstance();//可以精确到时分秒
//		System.out.println(String.format("%tR", date));

//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//            Date now = new Date();
//            SimpleDateFormat ds = new SimpleDateFormat("yyyy-MM-dd ");
//		    Date createTime = null;
//		String format = ds.format(now);
//		String s = format + "16:20";
//		Date date = strToDateLong(s);
//		try {
//
//			createTime = df.parse(format +"16:20");
//
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		System.out.println(now);
//		System.out.println(createTime);

//		JSONObject object = new JSONObject();
//		List<JSONObject> daily = new ArrayList<>();
//		object.put("total",daily);
//		System.out.println(object);

//		String openId = "hcEgQVoFtfCS32owY";
//		String tenantId = "121";
//		String tenantName = "1223";
//		String phone = "13952623256";
//		User user = new User();
//		user.setOpenId(openId)
//				.setTenantId(tenantId)
//				.setTenantName(tenantName)
//				.setPhone(phone);
//		//userMapper.updateUserInfo(user);
//		if (StringUtil.stringIsNotNull(tenantId)){
//			String url = HttpclientUtil.get("httpclient.page.get")+"tenantId="+tenantId+"&phoneNumber="+phone;
//			String httpGet = HttpclientUtil.httpGet(url);
//			JSONObject result = JSONObject.parseObject(httpGet);
//			JSONArray jsonArray = result.getJSONObject("resultData").getJSONArray("list");
//			System.out.println("result="+jsonArray);
//			if(jsonArray.isEmpty()||jsonArray.size()<1){
//				System.out.println("111");
//				//根据openId查询,然后获取
//				//findByOpenId
//				//保存到平台
//			}else {
//				System.out.println("222");
//			}
//		}


//		Date date = new Date();
//		JSONObject object = new JSONObject();
//		String str = DateUtil.dateToStr(date);
//		object.put("date",str);
//		String s = object.toString();
//		System.out.println(CommUtil.getGuid());


//		Boolean aaa = aaa();
//		System.out.println(aaa);
//
//		int[] ins = {1,2,3,4,5,6,7,8,9,10};
//		int[] inss = {10,9,8,6,3,2,0};
//		Boolean fun = fun(ins, 10);
//
//		Boolean funs = funs(inss, 10);
//		System.out.println(fun);
//		System.out.println(funs);

		List<Integer> PrList = new ArrayList<>();
		PrList.add(5);
		PrList.add(4);
		PrList.add(3);
		PrList.add(2);
		PrList.add(1);


        PrList.set(0,4);
//		for(int i=0;i<PrList.size();i++){
//			PrList.remove(1);
//		}
		PrList.remove(0);

		System.out.println(PrList.get(0));

//        int a1=2;
//		List<Integer> integers = PrList.subList(0, a1);
//		System.out.println("integers="+integers.size());
//		System.out.println("a1="+a1);
//
//		String type = "0"; //1递增取最大值 2递减取最大值 3绝对值小于b 取第一值 4绝对值大于等于b 取绝对值最大值
//		Boolean fun = fun(PrList); //true 递增  flase 递减
//		if (fun){
//			type = "1";
//		}else if(reduce(PrList)){
//			type = "2";
//		}
//		System.out.println(type);

//		int rint = (int) Math.ceil(10/3);
//		System.out.println(rint);
		recursion_display(5);

	}
	public static void recursion_display(int n) {
		int temp=n;//保证前后打印的值一样
		System.out.println("递进:" + temp);
		if (n > 0) {
			recursion_display(--n);
		}
		System.out.println("回归:" + temp);
	}

	public static int fib(int n) throws Exception {
		if (n < 0)
			throw new Exception("参数不能为负！");
		else if (n == 0 || n == 1)
			return n;
		else
			return fib(n - 1) + fib(n - 2);
	}
	//增加
	public static Boolean fun(List<Integer> integers) {
		for(int i=0;i<integers.size();i++) {
			if(i<integers.size()-1 ) {
				if(integers.get(i)>=integers.get(i+1))
					return false;
			}
		}
		return true;

	}
	//减少
	public static Boolean reduce(List<Integer> integers) {
		for(int i=0;i<integers.size();i++) {
			if(i<integers.size()-1 ) {
				if(integers.get(i)<=integers.get(i+1))
					return false;
			}
		}
		return true;

//		Boolean a = true;
//		for(int i = 0 ;i<integers.size();i++){
//			while(integers.get(i+1)<integers.get(i)){
//				return true;
//			}
//			a =false;
//			return false;
//		}
//		return a;
	}








	public static Date strToDateLong(String strDate) {
		   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		   ParsePosition pos = new ParsePosition(0);
		   Date strtodate = formatter.parse(strDate, pos);
		   return strtodate;
		}


	public static Boolean aaa() {
		int[] ins = {1,2,3,4,5,6,7,8,12,10};
		Boolean a = true;
		for(int i = 0 ;i<ins.length;i++){
			while(ins[i+1]>ins[i]){
				return true;
			}
			a =false;
			return false;
		}
		return a;
	}

	static Boolean fun(int a[], int n)
	{
		if( n == 1 || n == 0 )
			return true;
		if( n ==2 )
			return a[n-1] > a[n-2];
		return fun( a,n-1) && ( a[n-1] > a[n-2] );
	}

	static Boolean funs(int a[], int n)
	{
		if( n == 1 || n == 0 )
			return true;
		if( n ==2 )
			return a[n-1] < a[n-2];
		return fun( a,n-1) && ( a[n-1] < a[n-2] );
	}
}
