package com.ly.supermvp.delegate;

import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ly.supermvp.R;
import com.ly.supermvp.model.entity.ShowApiWeather;
import com.ly.supermvp.mvp_frame.view.AppDelegate;
import com.ly.supermvp.utils.GlideUtil;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.Holder;
import com.orhanobut.dialogplus.ViewHolder;
import com.rey.material.widget.EditText;

import butterknife.Bind;

/**
 * <Pre>
 * 天气预报界面视图代理
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *          <p/>
 *          Create by 2016/2/29 17:44
 */
public class WeatherFragmentDelegate extends AppDelegate {

    private ImageView iv_weather;
    private TextView tv_weather, tv_aqi, tv_sd, tv_wind_direction, tv_wind_power, tv_temperature_time,
            tv_temperature;
    private LinearLayout ll_holder;

    @Bind(R.id.et_location)
    EditText et_location;

    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_weather;
    }

    /**
     * 获取输入的地名
     * @return
     */
    public String getInputLocation(){
        return et_location.getText().toString();
    }

    /**
     * 显示当前天气弹窗
     */
    public void showNowWeatherDialog(ShowApiWeather weather) {
        ll_holder = (LinearLayout) getActivity().getLayoutInflater().inflate(R.layout.dialog_weather, null);
        Holder holder = new ViewHolder(ll_holder);
        findHolderChildView(holder);
        GlideUtil.loadImage(getActivity(), weather.now.weather_pic, iv_weather);
        tv_weather.setText(weather.now.weather);
        tv_temperature.setText(weather.now.temperature + "℃");
        tv_temperature_time.setText(weather.now.temperature_time);
        tv_aqi.setText("污染指数:" + weather.now.aqi);
        tv_sd.setText("湿度:" + weather.now.sd);
        tv_wind_direction.setText("风向:" + weather.now.wind_direction);
        tv_wind_power.setText("风力强度:" + weather.now.wind_power);
        showOnlyContentDialog(holder, Gravity.BOTTOM, false);
    }

    /**
     * 查找弹窗的holder的子控件
     *
     * @param holder
     */
    private void findHolderChildView(Holder holder) {
        iv_weather = (ImageView) holder.getInflatedView().findViewById(R.id.iv_weather);
        tv_weather = (TextView) holder.getInflatedView().findViewById(R.id.tv_weather);
        tv_temperature = (TextView) holder.getInflatedView().findViewById(R.id.tv_temperature);
        tv_temperature_time = (TextView) holder.getInflatedView().findViewById(R.id.tv_temperature_time);
        tv_aqi = (TextView) holder.getInflatedView().findViewById(R.id.tv_aqi);
        tv_sd = (TextView) holder.getInflatedView().findViewById(R.id.tv_sd);
        tv_wind_direction = (TextView) holder.getInflatedView().findViewById(R.id.tv_wind_direction);
        tv_wind_power = (TextView) holder.getInflatedView().findViewById(R.id.tv_wind_power);
    }

    /**
     * 仅显示内容的dialog
     *
     * @param holder
     * @param gravity         显示位置（居中，底部，顶部）
     * @param expanded        是否支持展开（有列表时适用）
     */
    private void showOnlyContentDialog(Holder holder, int gravity,
                                       boolean expanded) {
        final DialogPlus dialog = DialogPlus.newDialog(getActivity())
                .setContentHolder(holder)
                .setGravity(gravity)
                .setExpanded(expanded)
                .setCancelable(true)
                .create();
        dialog.show();
    }
}