/**
 * 
 */
package com.huidf.slimming.fragment.user.perfect_info;

import android.widget.ImageView;
import android.widget.TextView;

import com.huidf.slimming.R;
import com.huidf.slimming.base.BaseFragment;
import com.huidf.slimming.context.PreferenceEntity;
import com.huidf.slimming.view.loading.RadioHorizonalRuler;

import huitx.libztframework.utils.MathUtils;
import huitx.libztframework.utils.PreferencesUtils;

/**
 * 选择目标体重
 * @author ZhuTao
 * @date 2017/4/21 
 * @params 
*/

public class GuidanceTargetWeightFragment extends BaseFragment{


	private ImageView iv_guidance_target_weight;
	private TextView tv_guidance_target_weight;
	private RadioHorizonalRuler view_guidance_target_weight;
	private TextView tv_guidance_target_weight_value;
	private TextView tv_guidance_target_weight_hint;
	private TextView tv_guidance_target_weight_warning;

	private int sex = -1;


	public GuidanceTargetWeightFragment() {
		super(R.layout.fragment_guidance_target_weight);
	}


	@Override
	protected void initHead() {


	}
	@Override
	protected void initContent() {
		iv_guidance_target_weight = findViewByIds(R.id.iv_guidance_target_weight);
		tv_guidance_target_weight = findViewByIds(R.id.tv_guidance_target_weight);
		view_guidance_target_weight = findViewByIds(R.id.view_guidance_target_weight);
		tv_guidance_target_weight_value = findViewByIds(R.id.tv_guidance_target_weight_value);
		tv_guidance_target_weight_hint = findViewByIds(R.id.tv_guidance_target_weight_hint);
		tv_guidance_target_weight_warning = findViewByIds(R.id.tv_guidance_target_weight_warning);

	}
	@Override
	protected void initLocation() {
		mLayoutUtil.setIsFullScreen(true);
		mLayoutUtil.drawViewRBLayout(iv_guidance_target_weight, 132, 132, -1, -1, 140, -1);
		mLayoutUtil.drawViewRBLayout(tv_guidance_target_weight_value, 0, 0, 0, 0, 176, -1);
		mLayoutUtil.drawViewRBLayout(view_guidance_target_weight, 0, 110, -1, -1, 38, -1);
		mLayoutUtil.drawViewRBLayout(tv_guidance_target_weight_hint, 0, 0, 0, 0, 40, -1);
		mLayoutUtil.drawViewRBLayout(tv_guidance_target_weight_warning, 0, 0, 0, 0, 165, -1);
	}
	@Override
	protected void initLogic() {

	}

	@Override
	public void onResume()
	{
		super.onResume();

		int sex  =  MathUtils.stringToIntForPreference(PreferenceEntity.KEY_USER_SEX, 1);
		if(sex == 1) iv_guidance_target_weight.setBackgroundResource(R.drawable.iv_man_bef);
		else iv_guidance_target_weight.setBackgroundResource(R.drawable.iv_woman_bef);

		mWeight = MathUtils.stringToIntForPreference(PreferenceEntity.KEY_USER_INITIAL_WEIGHT, 50);
		mTargetWeight = MathUtils.stringToIntForPreference(PreferenceEntity.KEY_USER_TARGET_WEIGHT, mWeight);

		float height = MathUtils.stringToIntForPreference(PreferenceEntity.KEY_USER_HEIGHT, 100) * 0.01f;
		int minWeight = (int) (height*height*15.0f);
		warnWeight = (int) (height*height*18.0f);


		view_guidance_target_weight.initViewParam(mTargetWeight, mWeight, minWeight, 10);	//设置默认值，最大值，最小值，间隔
		//设置监听
		view_guidance_target_weight.setValueChangeListener(new RadioHorizonalRuler.OnValueChangeListener(){

			@Override
			public void onValueChange(int value) {
				updateView(value);
			}

		});
		tv_guidance_target_weight_value.setText(view_guidance_target_weight.getValue() + "KG");
		updateView(view_guidance_target_weight.getValue());
	}

	private void updateView(int value){
		mTargetWeight = value;
		if(warnWeight >= value) tv_guidance_target_weight_warning.setText("目标体重过轻");
		else tv_guidance_target_weight_warning.setText("");
		tv_guidance_target_weight_hint.setText("将减去" + (mWeight - value ) + "KG");
		tv_guidance_target_weight_value.setText(String.valueOf(value) + "KG");
	}

	private int mWeight,mTargetWeight,warnWeight;
	/** 保存数据并确定是否可以正常进行下一步 */
	public boolean isNext(){
		PreferencesUtils.putString(mContext, PreferenceEntity.KEY_USER_TARGET_WEIGHT, mTargetWeight + "");
		return true;

	}

	@Override
	protected void pauseClose() {

	}

	@Override
	protected void destroyClose() {

	}

	@Override
	public void paddingDatas(String mData, int type) {

	}

	@Override
	public void error(String msg, int type) {

	}
}