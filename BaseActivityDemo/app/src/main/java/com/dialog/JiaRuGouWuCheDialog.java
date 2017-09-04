package com.dialog;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.activity.R;
import com.base.BaseDialogFragment;
import com.bean.GoodsDetailBean;
import com.util.ByAlert;
import com.util.DensityUtil;
import com.weight.FlowLayout;
import com.weight.XScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/4 0004.
 */

public class JiaRuGouWuCheDialog extends BaseDialogFragment {
    View close;
    ImageView pic;
    ViewGroup guigeContainer;
    View ok;
    GoodsDetailBean goodsDetailBeans;
    View jian;
    View jia;
    EditText input;
    boolean isLiJIGouMai;
    ViewGroup container;
    String imgUrl;
    protected String price;
    private FlowLayout flowLayout;
    TextView txt_price;
    TextView txt_kc;
    int kucun;//标记库存
    String tempSkuId = null;
    private String gouwunum = "";//购物车的数量
    public JiaRuGouWuCheDialog() {
    }
    public JiaRuGouWuCheDialog(GoodsDetailBean goodsDetailBeans,
                               boolean isLiJIGouMai) {
        this.goodsDetailBeans = goodsDetailBeans;
        this.isLiJIGouMai = isLiJIGouMai;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return LayoutInflater.from(getContext()).inflate(
                R.layout.dialog_add_shopcart, null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //显示价格（点进去的时候显示最低价格和最高价格的区间）  当只有一个规格时显示一个价格
        double da = Double.parseDouble(goodsDetailBeans.getSku_list().get(0).getPrice());
        double xiao = Double.parseDouble(goodsDetailBeans.getSku_list().get(0).getPrice());
        if (goodsDetailBeans.getSku_list().size() > 1) {
            for (int i = 0; i < goodsDetailBeans.getSku_list().size(); i++) {
                double d = Double.parseDouble(goodsDetailBeans.getSku_list().get(i).getPrice());
                if (da < d) {
                    da = d;
                }
                if (xiao > d) {
                    xiao = d;
                }
            }
            txt_price.setText(String.valueOf(xiao / 100) + "-" + String.valueOf(da / 100) + "元/" + goodsDetailBeans.getGoods_unit_name());
        } else {
            txt_price.setText(String.valueOf(xiao / 100) + "元/" + goodsDetailBeans.getGoods_unit_name());
        }
        //显示库存(首次显示所有数量的和)
        int shuliang = 0;
        for (int i = 0; i < goodsDetailBeans.getSku_list().size(); i++) {
            int d = Integer.parseInt(goodsDetailBeans.getSku_list().get(i).getQuantity());
            shuliang += d;
        }
        kucun = shuliang;
        txt_kc.setText(String.valueOf(kucun) + goodsDetailBeans.getGoods_unit_name());

        if (goodsDetailBeans.getSku_info().size() <= 0) {//没有规格

        } else {//有规格

        }
        addGuiGe();
        jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sb = checkGuiGeSelectedState();
                if (sb == null) {
                    return;
                }
                String s = input.getText().toString();
                int ss = 0;
                if (!s.equals("")) {
                    ss = Integer.parseInt(s);
                }
                if (ss >= kucun) {
                    ByAlert.alert("库存不足,您的购物车已有" + (gouwunum.equals("") ? "0" : gouwunum) + goodsDetailBeans.getGoods_unit_name());
                    return;
                }
                try {
                    int c = Integer.parseInt(s);
                    input.setText(c + 1 + "");
                } catch (NumberFormatException e) {
                    input.setText("1");
                }
                input.setSelection(input.getText().length());
            }
        });
        jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sb = checkGuiGeSelectedState();
                if (sb == null) {
                    return;
                }
                String s = input.getText().toString();
                try {
                    int c = Integer.parseInt(s);
                    if (c > 1) {
                        c--;
                    }
                    input.setText(c + "");
                } catch (NumberFormatException e) {
                    input.setText("1");
                }
                input.setSelection(input.getText().length());
            }
        });
        close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                List<String> xuanzhong = selectedGuige();
                StringBuffer pkidTemp = new StringBuffer();
                StringBuffer stringBuffer = new StringBuffer();
                if (xuanzhong != null) {
                    for (String s1 : xuanzhong) {
                        stringBuffer.append(s1 + ";");
                        pkidTemp.append(s1 + ";");
//                        tempSkuId = pkidTemp.toString();
                    }
                    if (xuanzhong.size() <= 0) {
//                        tempSkuId = null;
                    }
                } else {
//                    tempSkuId = null;
                }
                for (int i = 0; i < goodsDetailBeans.getSku_list().size(); i++) {
                    String sid = goodsDetailBeans.getSku_list().get(i).getSku_id();

                    if (sid.equals(stringBuffer.toString())) {
//                        goods_guige.setText(goodsDetailBeans.getSku_list().get(i).getSku_desc());
                    }
                }
                closeDialog();
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            public String pskuId;

            @Override
            public void onClick(View v) {

                String s = checkGuiGeSelectedState();
                if (s == null) {
                    return;
                }
                List<GoodsDetailBean.SkuList> list = goodsDetailBeans
                        .getSku_list();
                for (GoodsDetailBean.SkuList skuCombinationBean : list) {
                    if (skuCombinationBean.getSku_id().equals(s)) {
                        pskuId = skuCombinationBean.getSku_id();
                        break;
                    }
                }
                ok.setEnabled(false);
                if (input.getText().toString().equals("") || input.getText().toString().equals("0")) {
                    ByAlert.alert("请输入有效数量");
                    return;
                }
                if (goodsDetailBeans.getSku_info().size() > 0) {//多规格
                    String guigeID = "";//规格id

                    List<String> xuanzhong = selectedGuige();
                    StringBuffer stringBuffer = new StringBuffer();
                    if (xuanzhong != null) {
                        for (String s1 : xuanzhong) {
                            stringBuffer.append(s1 + ";");
                        }
                    }
                    for (int i = 0; i < goodsDetailBeans.getSku_list().size(); i++) {
                        String sid = goodsDetailBeans.getSku_list().get(i).getSku_id();
                        String pkid = goodsDetailBeans.getSku_list().get(i).getSku_pkid();
                        if (sid.equals(stringBuffer.toString())) {
                            guigeID = pkid;
                        }
                    }
                    ByAlert.alert(goodsDetailBeans.getId() + "" + input.getText().toString());
//                    userPresenter.addMallAll(uid, goodsDetailBeans.getId(), guigeID, input.getText().toString());

                } else {//单规格
                    ByAlert.alert(goodsDetailBeans.getId() + "" + input.getText().toString());
//                    userPresenter.addMallAll(uid, goodsDetailBeans.getId(), goodsDetailBeans.getSku_pkid(), input.getText().toString());
                }
                dismiss();
            }
        });
        input.addTextChangedListener(watcher);
    }

    /**
     * 输入数量时候的监听
     */
    TextWatcher watcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //只要编辑框内容有变化就会调用该方法，s为编辑框变化后的内容
            Log.i("onTextChanged", s.toString());

        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //编辑框内容变化之前会调用该方法，s为编辑框内容变化之前的内容
            Log.i("beforeTextChanged", s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {
            //编辑框内容变化之后会调用该方法，s为编辑框内容变化后的内容
            Log.i("afterTextChanged", s.toString());
            if (!s.toString().equals("")) {
                int shuru = Integer.parseInt(s.toString());
                if (shuru > kucun) {
                    input.setText("1");
                }
            }
        }
    };

    protected String checkGuiGeSelectedState() {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < guigeContainer.getChildCount(); i++) {
            View vv = guigeContainer.getChildAt(i);
            if (!(vv instanceof FlowLayout)) {
                continue;
            }
            FlowLayout flowLayout = (FlowLayout) vv;
            int j;
            for (j = 0; j < flowLayout.getChildCount(); j++) {
                TextView textView = (TextView) flowLayout.getChildAt(j);
                if (textView.isSelected()) {
                    sb.append(textView.getTag() + ";");
                    break;
                }
            }
            if (j == flowLayout.getChildCount()) {
                ByAlert.alert("请选择商品");
                return null;
            }
        }
        return sb.toString();
    }


    //规格
    private void addGuiGe() {

        List<GoodsDetailBean.SkuInfo> list = goodsDetailBeans
                .getSku_info();
        for (int i = 0; i < list.size(); i++) {
            GoodsDetailBean.SkuInfo skuBean = list.get(i);
            TextView title = createTitle(skuBean.getSku_name());
            guigeContainer.addView(title);

            flowLayout = createFlowLayout(skuBean.getSku_id(),
                    goodsDetailBeans.getSku_info().get(i).getValue_list(),
                    "");
            guigeContainer.addView(flowLayout);
        }
        container.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {

                    @SuppressWarnings("deprecation")
                    @SuppressLint("NewApi")
                    @Override
                    public void onGlobalLayout() {
                        try {
                            container.getViewTreeObserver()
                                    .removeGlobalOnLayoutListener(this);
                        } catch (Throwable e) {
                        }
                        if (container.getHeight() > DensityUtil
                                .screenHeigh() * 0.7) {

                            int h = DensityUtil.screenHeigh()
                                    - DensityUtil.dip2px(280);
                            XScrollView scrollView = new XScrollView(
                                    getContext());
                            scrollView.setLayoutParams(new ViewGroup.LayoutParams(
                                    ViewGroup.LayoutParams.MATCH_PARENT, h));

                            ViewGroup vg = (ViewGroup) guigeContainer
                                    .getParent();
                            vg.removeView(guigeContainer);

                            scrollView.addView(guigeContainer);
                            vg.addView(scrollView);
                        }
                    }
                });
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (v.isSelected()) {
                v.setSelected(false);
                ((TextView) v).setTextColor(Color.BLACK);
                imgUrl = goodsDetailBeans.getMain_img();
                if ((!goodsDetailBeans.getMain_img().equals(""))
                        & goodsDetailBeans.getMain_img() != null) {
//                        XImageLoader.load(Const.imgurl + goodsDetailBeans.getMain_img(),pic);
//                    XImageLoader.load(Const.IMAGE_HEAD + goodsDetailBeans.getMain_img(), pic);
                    Log.d("XImageLoader", goodsDetailBeans.getMain_img() + "");
                }
                // txt_price.setText(Double.parseDouble(goodsDetailBeans.getSku_list().get(0).getPrice()) / 100 + "元/" + goodsDetailBeans.getGoods_unit_name());
                //   txt_kc.setText(goodsDetailBeans.getSku_list().get(0).getQuantity()+ goodsDetailBeans.getGoods_unit_name());
                //显示价格（未选中时显示最低价格和最高价格的区间）  当只有一个规格时显示一个价格
                double MaxPrice = Double.parseDouble(goodsDetailBeans.getSku_list().get(0).getPrice());
                double SmallPrice = Double.parseDouble(goodsDetailBeans.getSku_list().get(0).getPrice());
                if (goodsDetailBeans.getSku_list().size() > 1) {
                    for (int i = 0; i < goodsDetailBeans.getSku_list().size(); i++) {
                        double SelectedPrice = Double.parseDouble(goodsDetailBeans.getSku_list().get(i).getPrice());
                        if (MaxPrice < SelectedPrice) {
                            MaxPrice = SelectedPrice;
                        }
                        if (SmallPrice > SelectedPrice) {
                            SmallPrice = SelectedPrice;
                        }
                    }
                    txt_price.setText(String.valueOf(SmallPrice / 100) + "-" + String.valueOf(MaxPrice / 100) + "元/" + goodsDetailBeans.getGoods_unit_name());
                } else {
                    txt_price.setText(String.valueOf(SmallPrice / 100) + "元/" + goodsDetailBeans.getGoods_unit_name());
                }

                //显示库存(未选中显示所有数量的和)
                int shuliang = 0;
                for (int i = 0; i < goodsDetailBeans.getSku_list().size(); i++) {
                    int d = Integer.parseInt(goodsDetailBeans.getSku_list().get(i).getQuantity());
                    shuliang += d;
                }
                kucun = shuliang;
                txt_kc.setText(String.valueOf(kucun) + goodsDetailBeans.getGoods_unit_name());

                return;
            }
            FlowLayout clickTextViewParent = null;
            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < guigeContainer.getChildCount(); i++) {
                View view = guigeContainer.getChildAt(i);
                if (!(view instanceof FlowLayout)) {
                    continue;
                }
                FlowLayout flowLayout = (FlowLayout) view;
                if (v.getParent() == flowLayout) {
                    clickTextViewParent = flowLayout;
                    stringBuilder.append(v.getTag());
                    stringBuilder.append(";");

                } else {
                    for (int j = 0; j < flowLayout.getChildCount(); j++) {
                        View textview = flowLayout.getChildAt(j);
                        if (textview.isSelected()) {
                            stringBuilder.append(textview.getTag());
                            stringBuilder.append(";");
                            break;
                        }
                    }
                }
            }
            List<GoodsDetailBean.SkuList> skuList = goodsDetailBeans.getSku_list();
            String selectedSku = stringBuilder.toString();
            for (int i = 0; i < skuList.size(); i++) {
                String sku = skuList.get(i).getSku_id();
                String kc = skuList.get(i).getQuantity();
                for (int j = 0; j < clickTextViewParent.getChildCount(); j++) {
                    if (clickTextViewParent.getChildAt(j).isSelected()) {
                        clickTextViewParent.getChildAt(j).setSelected(false);
                        ((TextView) clickTextViewParent.getChildAt(j)).setTextColor(Color.BLACK);
                        break;
                    }
                }
                v.setSelected(true);
                // txt_price.setText(Double.parseDouble(v.getTag(R.id.tag_first) + "") / 100 + "元/" + goodsDetailBeans.getGoods_unit_name());//（选择两个的时候显示的是当前点击的标签价格）
                ((TextView) v).setTextColor(Color.WHITE);
                if (sku.equals(selectedSku)) {
                    if (kc.equals("0")) {
                        ByAlert.alert("该商品没有库存了");
                        ok.setEnabled(false);//没有库存不可点击
                    } else {
                        ok.setEnabled(true);
                    }
                    txt_price.setText(Double.parseDouble(skuList.get(i).getPrice()) / 100 + "元/" + goodsDetailBeans.getGoods_unit_name());
                    kucun = Integer.parseInt(goodsDetailBeans.getSku_list().get(i).getQuantity());
                    txt_kc.setText(kucun + goodsDetailBeans.getGoods_unit_name());
                }

            }
            String s = isAllFenLeiSelected();
            if (s != null) {
//                        ((View) kucun.getParent()).setVisibility(View.VISIBLE);
                List<GoodsDetailBean.SkuList> list = goodsDetailBeans
                        .getSku_list();
                for (GoodsDetailBean.SkuList skuCombinationBean : list) {
                    if (skuCombinationBean.getSku_id().equals(s)) {
//                                kucun.setText(skuCombinationBean.getQuantity()
//                                        + "");
                        price = skuCombinationBean.getPrice();

                        if (TextUtils.isEmpty(skuCombinationBean
                                .getIcon_url())) {
//                            XImageLoader.load(Const.IMAGE_HEAD + goodsDetailBeans.getMain_img(), pic);
                            Log.d("XImageLoader", goodsDetailBeans.getMain_img() + "");
                            imgUrl = goodsDetailBeans.getMain_img();
                        } else {
//                            XImageLoader.load(Const.IMAGE_HEAD + skuCombinationBean.getIcon_url(), pic);
                            Log.d("XImageLoader", goodsDetailBeans.getMain_img() + "");
                            imgUrl = skuCombinationBean.getIcon_url();
                        }
                        break;
                    }
                }
            }
        }
    };

    private String isAllFenLeiSelected() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < guigeContainer.getChildCount(); i++) {
            View vv = guigeContainer.getChildAt(i);
            if (!(vv instanceof FlowLayout)) {
                continue;
            }
            FlowLayout flowLayout = (FlowLayout) vv;
            int j;
            for (j = 0; j < flowLayout.getChildCount(); j++) {
                TextView textView = (TextView) flowLayout.getChildAt(j);
                if (textView.isSelected()) {
                    sb.append(textView.getTag() + ";");
                    break;
                }
            }
            if (j == flowLayout.getChildCount()) {
                return null;
            }
        }
        return sb.toString();
    }

    /**
     * 当前已选中的规格集合
     *
     * @return
     */
    protected List<String> selectedGuige() {

        List<String> selectedGuige = new ArrayList<String>();
        for (int i = 0; i < guigeContainer.getChildCount(); i++) {
            View vv = guigeContainer.getChildAt(i);
            if (!(vv instanceof FlowLayout)) {
                continue;
            }
            FlowLayout flowLayout = (FlowLayout) vv;
            for (int j = 0; j < flowLayout.getChildCount(); j++) {
                TextView textView = (TextView) flowLayout.getChildAt(j);
                if (textView.isSelected()) {
                    selectedGuige.add((String) textView.getTag());
                }
            }
        }
        return selectedGuige;
    }

    private FlowLayout createFlowLayout(String vid, List<GoodsDetailBean.ValueList> id,
                                        String str) {
        int dis = DensityUtil.dip2px(5);
        FlowLayout flowLayout = new FlowLayout(getContext());
        for (int i = 0; i < id.size(); i++) {
            TextView textView = new TextView(getContext());
            textView.setTextColor(Color.BLACK);
            textView.setBackgroundResource(R.drawable.selector_guige);
            textView.setText(id.get(i).getValue_name());
            textView.setTag(vid + ":" + id.get(i).getValue_id());
            textView.setTag(R.id.tag_first, goodsDetailBeans.getSku_list().get(i).getPrice());
            textView.setOnClickListener(clickListener);
            if (tempSkuId != null) {
                String[] strTemp = tempSkuId.split(";");
                for (String s : strTemp) {
                    if (s.equals(textView.getTag().toString())) {
                        textView.setSelected(true);
                    }
                }

            }
            ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            marginLayoutParams.setMargins(dis, dis, dis, dis);
            textView.setLayoutParams(marginLayoutParams);
            flowLayout.addView(textView);
        }
        return flowLayout;
    }

    /**
     * 设置套餐一、套餐二等标题栏
     *
     * @param s
     * @return
     */
    private TextView createTitle(String s) {
        TextView textView = new TextView(getContext());
        textView.setText(s);
        textView.setTextColor(Color.BLACK);
        return textView;
    }

    public void closeDialog() {
        View v = getView().findViewById(R.id.container);
        ObjectAnimator animator = ObjectAnimator.ofFloat(v, "translationY",
                0, v.getHeight()).setDuration(300);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator arg0) {
            }

            @Override
            public void onAnimationRepeat(Animator arg0) {
            }

            @Override
            public void onAnimationEnd(Animator arg0) {
                dismiss();
            }

            @Override
            public void onAnimationCancel(Animator arg0) {
                dismiss();
            }
        });
        animator.start();
    }
}
