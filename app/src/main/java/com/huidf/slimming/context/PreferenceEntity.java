package com.huidf.slimming.context;

import android.util.Log;
import com.huidf.slimming.entity.user.UserEntity;
import org.xutils.http.RequestParams;

import java.util.HashMap;

import huitx.libztframework.context.LibPreferenceEntity;
import huitx.libztframework.utils.LOGUtils;
import huitx.libztframework.utils.MD5Utils;
import huitx.libztframework.utils.NewWidgetSetting;
import huitx.libztframework.utils.PreferencesUtils;

public class PreferenceEntity extends LibPreferenceEntity{
//	/** 本地缓存地址 mnt/sdcard/huidf_doc */
//	public static final String KEY_CACHE_PATH = "/huidf_slimming";
	// 清除文件夹里面的文件！
	// boolean file = DelectFileUties.delAllFile("mnt/sdcard/menmen/");
	// if (file) {
	// ToastUtils.show(ApplicationData.context, "清除缓存成功！");
	// }else {
	// ToastUtils.show(ApplicationData.context, "清除缓存失败");
	// }

	/** 是否同步用户id（H5 LocalStorage缓存）  */
	public static boolean isSyncUserDatas = false;

	/** 判断是否已经登录 */
	public static boolean isLogin(){
		String user_id = PreferencesUtils.getString(ApplicationData.context, PreferenceEntity.KEY_USER_ID, "");
		String user = PreferencesUtils.getString(ApplicationData.context, PreferenceEntity.KEY_USER_ACCOUNT, "");
		String imei = PreferencesUtils.getString(ApplicationData.context, PreferenceEntity.KEY_USER_IMEI, "");
//		if(imei.equals(ApplicationData.imei) && user_id !=null && !user_id.equals("") && user !=null && !user.equals("")) return true;
		if(!NewWidgetSetting.filtrationStringbuffer(imei,"").equals("") && user_id !=null && !user_id.equals("") && user !=null && !user.equals("")) return true;
		else return false;
	}

	public static void setUserEntity(UserEntity.Data data) {
		clearData();
		PreferencesUtils.putString(ApplicationData.context, KEY_USER_ID, "" + data.id);
		PreferencesUtils.putString(ApplicationData.context, KEY_USER_ACCOUNT, "" + data.phone);
		PreferencesUtils.putString(ApplicationData.context, KEY_USER_AGE, NewWidgetSetting.getInstance().filtrationStringbuffer(data.age, "18"));
		PreferencesUtils.putString(ApplicationData.context, KEY_USER_SEX, NewWidgetSetting.getInstance().filtrationStringbuffer(data.sex, "1"));
		PreferencesUtils.putString(ApplicationData.context, KEY_USER_NICK, ""	+ (data.name != null ? data.name : ""));
		PreferencesUtils.putString(ApplicationData.context, KEY_USER_HEADER, ""		+ (data.head != null ? data.head : ""));
		PreferencesUtils.putString(ApplicationData.context, KEY_USER_IMEI, NewWidgetSetting.getInstance().filtrationStringbuffer(data.imei, ""));
		PreferencesUtils.putString(ApplicationData.context, KEY_USER_ISALL,NewWidgetSetting.getInstance().filtrationStringbuffer(data.isall, "0"));
		PreferencesUtils.putString(ApplicationData.context, KEY_USER_MONEY, NewWidgetSetting.getInstance().filtrationStringbuffer(data.money, "0"));
		PreferencesUtils.putString(ApplicationData.context, KEY_APP_UPDATE_URL, NewWidgetSetting.getInstance().filtrationStringbuffer(data.url, ""));
		PreferencesUtils.putInt(ApplicationData.context, KEY_APP_UPDATE_VERSION, data.version );
//		PreferencesUtils.putString(ApplicationData.context, KEY_APP_WX_QQ_UNIONID, NewWidgetSetting.getInstance().filtrationStringbuffer(data.unionId,"0"));
		PreferencesUtils.putString(ApplicationData.context, KEY_USER_SIGNATURE, "" + NewWidgetSetting.getInstance().filtrationStringbuffer(data.sign, ""));
		PreferencesUtils.putString(ApplicationData.context, KEY_USER_INITIAL_WEIGHT, "" + NewWidgetSetting.getInstance().filtrationStringbuffer(data.weight, ""));
		PreferencesUtils.putString(ApplicationData.context, KEY_USER_CURRENT_WEIGHT, "" + NewWidgetSetting.getInstance().filtrationStringbuffer(data.latestWeight, ""));
		PreferencesUtils.putString(ApplicationData.context, KEY_USER_TARGET_WEIGHT, "" + NewWidgetSetting.getInstance().filtrationStringbuffer(data.targetWeight, ""));
		PreferencesUtils.putString(ApplicationData.context, KEY_USER_SIGNATURE, "" + NewWidgetSetting.getInstance().filtrationStringbuffer(data.sign, ""));
	}

	/** 清空用户信息 */
	public static void clearData() {
		PreferencesUtils.putString(ApplicationData.context, KEY_USER_ID, "");
		PreferencesUtils.putString(ApplicationData.context, KEY_USER_ACCOUNT, "");
		PreferencesUtils.putString(ApplicationData.context, KEY_USER_HEADER, "");
		PreferencesUtils.putString(ApplicationData.context, KEY_USER_IMEI, "");
		PreferencesUtils.putString(ApplicationData.context, KEY_USER_SEX, "");
//		PreferencesUtils.putString(ApplicationData.context, KEY_USER_BIR, "");
		PreferencesUtils.putString(ApplicationData.context, KEY_USER_AGE, "");
		PreferencesUtils.putString(ApplicationData.context, KEY_USER_NICK, "");
		PreferencesUtils.putString(ApplicationData.context, KEY_USER_ISALL, "");
		PreferencesUtils.putString(ApplicationData.context, KEY_USER_DOCID, "");
		PreferencesUtils.putString(ApplicationData.context, KEY_USER_MONEY, "");
//		PreferencesUtils.putString(ApplicationData.context, KEY_APP_WX_QQ_UNIONID, "");
		PreferencesUtils.putString(ApplicationData.context, KEY_USER_SIGNATURE, "");
		PreferencesUtils.putString(ApplicationData.context, KEY_USER_INITIAL_WEIGHT, "");
		PreferencesUtils.putString(ApplicationData.context, KEY_USER_CURRENT_WEIGHT, "");
		PreferencesUtils.putString(ApplicationData.context, KEY_USER_TARGET_WEIGHT, "");
	}

	/** 用户个人信息 */
	public static void setUserInfoEntity(UserEntity.Data data) {
		PreferencesUtils.putString(ApplicationData.context, KEY_USER_SIGNATURE, NewWidgetSetting.getInstance().filtrationStringbuffer(data.sign, ""));
		PreferencesUtils.putString(ApplicationData.context, KEY_USER_ACCOUNT, NewWidgetSetting.getInstance().filtrationStringbuffer(data.account	, ""));
		PreferencesUtils.putString(ApplicationData.context, KEY_USER_NICK, NewWidgetSetting.getInstance().filtrationStringbuffer(data.name	, ""));
		PreferencesUtils.putString(ApplicationData.context, KEY_USER_HEADER, NewWidgetSetting.getInstance().filtrationStringbuffer(data.head, ""));
		PreferencesUtils.putString(ApplicationData.context, KEY_USER_BMI, NewWidgetSetting.getInstance().filtrationStringbuffer(data.bmi, ""));
		PreferencesUtils.putString(ApplicationData.context, KEY_USER_HEIGHT, NewWidgetSetting.getInstance().filtrationStringbuffer(data.height, ""));
		PreferencesUtils.putString(ApplicationData.context, KEY_USER_SEX, NewWidgetSetting.getInstance().filtrationStringbuffer(data.sex, ""));
		PreferencesUtils.putString(ApplicationData.context, KEY_USER_AGE, NewWidgetSetting.getInstance().filtrationStringbuffer(data.age, ""));
		PreferencesUtils.putString(ApplicationData.context, KEY_USER_BIR, NewWidgetSetting.getInstance().filtrationStringbuffer(data.birthday, ""));
		PreferencesUtils.putString(ApplicationData.context, KEY_USER_INITIAL_WEIGHT, NewWidgetSetting.getInstance().filtrationStringbuffer(data.weight, ""));
		PreferencesUtils.putString(ApplicationData.context, KEY_USER_CURRENT_WEIGHT, NewWidgetSetting.getInstance().filtrationStringbuffer(data.latestWeight, ""));
		PreferencesUtils.putString(ApplicationData.context, KEY_USER_TARGET_WEIGHT, NewWidgetSetting.getInstance().filtrationStringbuffer(data.targetWeight, ""));
		PreferencesUtils.putString(ApplicationData.context, KEY_USER_LOSE_WEIGHT_PERIOD, NewWidgetSetting.getInstance().filtrationStringbuffer(data.targetCycle, ""));
		PreferencesUtils.putString(ApplicationData.context, KEY_USER_DYNAMIC, NewWidgetSetting.getInstance().filtrationStringbuffer(data.count, ""));
		PreferencesUtils.putString(ApplicationData.context, KEY_USER_TARGET_WEIGHT_TIME, NewWidgetSetting.getInstance().filtrationStringbuffer(data.targetTime, ""));
//		PreferencesUtils.putString(ApplicationData.context, KEY_PRAISE_NUM, NewWidgetSetting.getInstance().filtrationStringbuffer(data.count, "0"));
//		PreferencesUtils.putString(ApplicationData.context, KEY_ATT_NUM, NewWidgetSetting.getInstance().filtrationStringbuffer(data.count2, "0"));
//		PreferencesUtils.putString(ApplicationData.context, KEY_FANS_NUM, NewWidgetSetting.getInstance().filtrationStringbuffer(data.count3, "0"));
//		PreferencesUtils.putString(ApplicationData.context, KEY_USER_MONEY, NewWidgetSetting.getInstance().filtrationStringbuffer(data.money, "0"));
	}

	/** 清空用户信息 */
	public static void clearUserInfoData() {
		PreferencesUtils.putString(ApplicationData.context, KEY_USER_SIGNATURE, "");
		PreferencesUtils.putString(ApplicationData.context, KEY_USER_ACCOUNT, "");
		PreferencesUtils.putString(ApplicationData.context, KEY_USER_NICK, "");
		PreferencesUtils.putString(ApplicationData.context, KEY_USER_HEADER, "");
		PreferencesUtils.putString(ApplicationData.context, KEY_USER_BMI, "");
		PreferencesUtils.putString(ApplicationData.context, KEY_USER_HEIGHT, "");
		PreferencesUtils.putString(ApplicationData.context, KEY_USER_SEX, "");
		PreferencesUtils.putString(ApplicationData.context, KEY_USER_AGE, "");
		PreferencesUtils.putString(ApplicationData.context, KEY_USER_BIR, "");
		PreferencesUtils.putString(ApplicationData.context, KEY_USER_CURRENT_WEIGHT, "");
		PreferencesUtils.putString(ApplicationData.context, KEY_USER_INITIAL_WEIGHT, "");
		PreferencesUtils.putString(ApplicationData.context, KEY_USER_TARGET_WEIGHT, "");
		PreferencesUtils.putString(ApplicationData.context, KEY_USER_LOSE_WEIGHT_PERIOD, "");
		PreferencesUtils.putString(ApplicationData.context, KEY_USER_DYNAMIC, "");
		PreferencesUtils.putString(ApplicationData.context, KEY_USER_TARGET_WEIGHT_TIME, "");
//		PreferencesUtils.putString(ApplicationData.context, KEY_PRAISE_NUM, "");
//		PreferencesUtils.putString(ApplicationData.context, KEY_ATT_NUM, "");
//		PreferencesUtils.putString(ApplicationData.context, KEY_FANS_NUM, "");
//		PreferencesUtils.putString(ApplicationData.context, KEY_USER_MONEY, "");
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static HashMap getLoginData() {
		HashMap ha = new HashMap();

//		String imei = ApplicationData.imei;
		String imei = PreferencesUtils.getString(ApplicationData.context, PreferenceEntity.KEY_USER_IMEI, "");
		String user_id = PreferencesUtils.getString(ApplicationData.context, PreferenceEntity.KEY_USER_ID, "");
		String user = PreferencesUtils.getString(ApplicationData.context, PreferenceEntity.KEY_USER_ACCOUNT, "");
		String password = PreferencesUtils.getString(ApplicationData.context,
				PreferenceEntity.KEY_USER_PSW, "");
		String psw = MD5Utils.md5((MD5Utils.md5(password) + ApplicationData.imei));// 用户加密后的密码！

		ha.put("id", user_id);// 用户的id
		ha.put("imei", imei);// 用户手机的im号
		ha.put("user", user);// 获取到用户的帐号
		ha.put("type", 0 + "");// type
		ha.put("psw", psw);// 保存用户加密后的密码

		return ha;
	}

	public  static RequestParams getLoginParams(){
		RequestParams params = new RequestParams();
		HashMap map = PreferenceEntity.getLoginData();
		params.addHeader("id", map.get("id") + "");// 用户id
		params.addHeader("user", map.get("user") + "");// 用户名
		params.addHeader("imei", map.get("imei") + "");// 手机唯一码
		return params;
	}

	// ***********************************用户信息
	/** 记录用户的id，long型 */
	public static final String KEY_USER_ID = "user_id";
	/** 签名 */
	public static final String KEY_USER_SIGNATURE = "user_signature";
	public static final String KEY_PRAISE_NUM = "user_praise_num";
	public static final String KEY_FANS_NUM = "user_fans_num";
	public static final String KEY_ATT_NUM = "user_att_num";
	public static final String KEY_USER_IMEI = "user_imei";
	public static final String KEY_USER_MONEY = "user_money";
	public static final String KEY_USER_DOCID = "user_docid";
	public static final String KEY_USER_QATEXTID = "qatextid";
	public static final String KEY_USER_LOGINISCHAT = "longinischat";
	public static final String KEY_USER_NICK = "user_nick";
	public static final String KEY_USER_NAME = "user_name";
	public static final String KEY_USER_HEADER = "user_header";
	public static final String KEY_USER_HEADER_CACHE = "user_header_cache";
	public static final String KEY_APP_UPDATE_VERSION= "app_version";
	public static final String KEY_APP_WX_QQ_UNIONID = "wx_qq_unionId";
	public static final String KEY_APP_UPDATE_URL = "app_url";
	public static final String KEY_USER_CARE_DATA = "user_care_data";
	/** 是否补全用户信息 */
	public static final String KEY_USER_ISALL = "isall";

	public static final String KEY_USER_SEX = "user_sex";
	public static final String KEY_USER_BIR = "user_bir";
	public static final String KEY_USER_AGE = "user_age";
	public static final String KEY_USER_LBS = "user_lbs";
	/** 帐号 */
	public static final String KEY_USER_ACCOUNT = "user_account";
	/** 密码 */
	public static final String KEY_USER_PSW = "user_psw";

	public static final String KEY_USER_HEIGHT = "user_height"; // 身高 float
	/** 最新体重 float */
	public static final String KEY_USER_CURRENT_WEIGHT = "user_weight";
	/** 初始体重 注册时填充的值，固定不变 */
	public static final String KEY_USER_INITIAL_WEIGHT = "user_current_weight";
	/** 目标体重 */
	public static final String KEY_USER_TARGET_WEIGHT = "user_target_weight";
	public static final String KEY_USER_LOSE_WEIGHT_PERIOD = "user_lose_period"; // 目标减肥周期
	public static final String KEY_USER_TARGET_WEIGHT_TIME = "user_target_time"; // 目标减肥时间
	public static final String KEY_USER_BMI = "user_bmi"; // bmi
	public static final String KEY_USER_DYNAMIC = "user_dynamic";	//动态数
	/** 目标减肥周期时间戳格式，接口端需要 */
	public static Long ValueLostWeightTime = 0L;

	/** 生日 eg: 2015-01-05 */
	public static String perfectInfoBirthday = "";
	public static final String perfectInfoBirthday_year = "perfectInfoBirthday_year";
	public static final String perfectInfoBirthday_month = "perfectInfoBirthday_month";
	public static final String perfectInfoBirthday_day = "perfectInfoBirthday_day";
	// 第三方

	/** 是否登录 */
	public static boolean isLogin=false;
	/** 标记是不是第一次打开APP */
	public static String KEY_IS_FIRST_OPEN = "";

	// ***********************************用户信息
}
