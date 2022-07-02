package com.example.yiapp.gauge;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.yiapp.MainActivity;
import com.example.yiapp.R;
import com.example.yiapp.base.BaseActivity;
import com.example.yiapp.data.Gauge;

import org.litepal.LitePal;

import es.dmoral.toasty.Toasty;

public class AddGaugeActivity extends BaseActivity {

    EditText name, nl, sjynx, sg, tz, touw, yw, tunw, ssy, szy, xl, mmsedf, gdsdf, adldf, npidf, vftdf, cdrdf;
    RadioGroup xb, ls, zy, ysr, hyzk, zn, xg, gxy, tnb, gxb, gxz, px, nxg, nws, lngr, ymgr, jzb, yyz, pjs;

    int userId;
    int gaugeId;
    int addlog;

    private void init() {
        name = find(R.id.name);
        nl = find(R.id.nl);
        sjynx = find(R.id.sjynx);
        sg = find(R.id.sg);
        tz = find(R.id.tz);
        touw = find(R.id.touw);
        yw = find(R.id.yw);
        tunw = find(R.id.tunw);
        ssy = find(R.id.ssy);
        szy = find(R.id.szy);
        xl = find(R.id.xl);
        mmsedf = find(R.id.mmse_df);
        gdsdf = find(R.id.gds_df);
        adldf = find(R.id.adl_df);
        npidf = find(R.id.npi_df);
        vftdf = find(R.id.vft_df);
        cdrdf = find(R.id.cdr_df);
        xb = find(R.id.xb);
        ls = find(R.id.ls);
        zy = find(R.id.zy);
        ysr = find(R.id.ysr);
        hyzk = find(R.id.hyzk);
        zn = find(R.id.zn);
        xg = find(R.id.xg);
        gxy = find(R.id.gxy);
        tnb = find(R.id.tnb);
        gxb = find(R.id.gxb);
        gxz = find(R.id.gxz);
        px = find(R.id.px);
        nxg = find(R.id.nxg);
        nws = find(R.id.nws);
        lngr = find(R.id.lngr);
        ymgr = find(R.id.ymgr);
        jzb = find(R.id.jzb);
        yyz = find(R.id.yyz);
        pjs = find(R.id.pjs);
    }

    private void setView() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        addlog = bundle.getInt("addlog");
        if (addlog == 0) {
            userId = bundle.getInt("userid");
        } else {
            gaugeId = bundle.getInt("gaugeid");
            init_gauge();
        }
    }

    private void init_gauge() {
        Gauge gauge = LitePal.find(Gauge.class, gaugeId);
        name.setText(gauge.getName());
        nl.setText(gauge.getNl());
        sjynx.setText(gauge.getSjynx());
        sg.setText(gauge.getSg());
        tz.setText(gauge.getTz());
        touw.setText(gauge.getTouw());
        yw.setText(gauge.getYw());
        tunw.setText(gauge.getTunw());
        ssy.setText(gauge.getSsy());
        szy.setText(gauge.getSzy());
        xl.setText(gauge.getXl());
        mmsedf.setText(gauge.getMmse_df());
        gdsdf.setText(gauge.getGds_df());
        adldf.setText(gauge.getAdl_df());
        npidf.setText(gauge.getNpi_df());
        vftdf.setText(gauge.getVft_df());
        cdrdf.setText(gauge.getCdr_df());

        switch (gauge.getXb()) {
            case "男":
                xb.check(R.id.nan);
                break;
            case "女":
                xb.check(R.id.nv);
                break;
        }
        switch (gauge.getLs()) {
            case "左利手":
                ls.check(R.id.zuo);
                break;
            case "右利手":
                ls.check(R.id.you);
                break;
            case "双利手":
                ls.check(R.id.shuang);
                break;
        }
        switch (gauge.getZy()) {
            case "干部":
                zy.check(R.id.zy1);
                break;
            case "农民":
                zy.check(R.id.zy2);
                break;
            case "个体及商业人员":
                zy.check(R.id.zy3);
                break;
            case "工人":
                zy.check(R.id.zy4);
                break;
            case "家务":
                zy.check(R.id.zy5);
                break;
            case "其他":
                zy.check(R.id.zy6);
                break;
        }
        switch (gauge.getYsr()) {
            case "小于1千":
                ysr.check(R.id.ysr1);
                break;
            case "1至2千":
                ysr.check(R.id.ysr2);
                break;
            case "2至3千":
                ysr.check(R.id.ysr3);
                break;
            case "3至4千":
                ysr.check(R.id.ysr4);
                break;
            case "大于4千":
                ysr.check(R.id.ysr5);
                break;
        }
        switch (gauge.getHyzk()) {
            case "已婚":
                hyzk.check(R.id.hyzk1);
                break;
            case "未婚":
                hyzk.check(R.id.hyzk2);
                break;
            case "离婚":
                hyzk.check(R.id.hyzk3);
                break;
            case "丧偶":
                hyzk.check(R.id.hyzk4);
                break;
        }
        switch (gauge.getZn()) {
            case "无":
                zn.check(R.id.zn1);
                break;
            case "1个":
                zn.check(R.id.zn2);
                break;
            case "2个":
                zn.check(R.id.zn3);
                break;
            case "3个以上":
                zn.check(R.id.zn4);
                break;
        }
        switch (gauge.getXg()) {
            case "中性均衡":
                xg.check(R.id.xg1);
                break;
            case "外向适应好":
                xg.check(R.id.xg2);
                break;
            case "外向适应差":
                xg.check(R.id.xg3);
                break;
            case "内向适应好":
                xg.check(R.id.xg4);
                break;
            case "内向适应差":
                xg.check(R.id.xg5);
                break;
            case "神经质":
                xg.check(R.id.xg6);
                break;
        }
        switch (gauge.getGxy()) {
            case "是":
                gxy.check(R.id.gxy1);
                break;
            case "否":
                gxy.check(R.id.gxy2);
                break;
        }
        switch (gauge.getTnb()) {
            case "是":
                tnb.check(R.id.tnb1);
                break;
            case "否":
                tnb.check(R.id.tnb2);
                break;
        }
        switch (gauge.getGxb()) {
            case "是":
                gxb.check(R.id.gxb1);
                break;
            case "否":
                gxb.check(R.id.gxb2);
                break;
        }
        switch (gauge.getGxz()) {
            case "是":
                gxz.check(R.id.gxz1);
                break;
            case "否":
                gxz.check(R.id.gxz2);
                break;
        }
        switch (gauge.getPx()) {
            case "是":
                px.check(R.id.px1);
                break;
            case "否":
                px.check(R.id.px2);
                break;
        }
        switch (gauge.getNxg()) {
            case "是":
                nxg.check(R.id.nxg1);
                break;
            case "否":
                nxg.check(R.id.nxg2);
                break;
        }
        switch (gauge.getNws()) {
            case "是":
                nws.check(R.id.nws1);
                break;
            case "否":
                nws.check(R.id.nws2);
                break;
        }
        switch (gauge.getLngr()) {
            case "是":
                lngr.check(R.id.lngr1);
                break;
            case "否":
                lngr.check(R.id.lngr2);
                break;
        }
        switch (gauge.getYmgr()) {
            case "是":
                ymgr.check(R.id.ymgr1);
                break;
            case "否":
                ymgr.check(R.id.ymgr2);
                break;
        }
        switch (gauge.getJzb()) {
            case "是":
                jzb.check(R.id.jzb1);
                break;
            case "否":
                jzb.check(R.id.jzb2);
                break;
        }
        switch (gauge.getYyz()) {
            case "是":
                yyz.check(R.id.yyz1);
                break;
            case "否":
                yyz.check(R.id.yyz2);
                break;
        }
        switch (gauge.getPjs()) {
            case "是":
                pjs.check(R.id.pjs1);
                break;
            case "否":
                pjs.check(R.id.pjs2);
                break;
        }
    }

    @Override
    protected void initViews() {
        init();
        setView();
    }

    public void finish(View view) {
        finish();
    }

    protected boolean judge() {
        if (name.getText().toString().equals("")) {
            return false;
        } else if (nl.getText().toString().equals("")) {
            return false;
        } else if (sjynx.getText().toString().equals("")) {
            return false;
        } else if (sg.getText().toString().equals("")) {
            return false;
        } else if (tz.getText().toString().equals("")) {
            return false;
        } else if (touw.getText().toString().equals("")) {
            return false;
        } else if (yw.getText().toString().equals("")) {
            return false;
        } else if (tunw.getText().toString().equals("")) {
            return false;
        } else if (ssy.getText().toString().equals("")) {
            return false;
        } else if (szy.getText().toString().equals("")) {
            return false;
        } else if (xl.getText().toString().equals("")) {
            return false;
        } else if (mmsedf.getText().toString().equals("")) {
            return false;
        } else if (gdsdf.getText().toString().equals("")) {
            return false;
        } else if (adldf.getText().toString().equals("")) {
            return false;
        } else if (npidf.getText().toString().equals("")) {
            return false;
        } else if (vftdf.getText().toString().equals("")) {
            return false;
        } else if (cdrdf.getText().toString().equals("")) {
            return false;
        } else if (xb.getCheckedRadioButtonId() == -1) {
            return false;
        } else if (ls.getCheckedRadioButtonId() == -1) {
            return false;
        } else if (zy.getCheckedRadioButtonId() == -1) {
            return false;
        } else if (ysr.getCheckedRadioButtonId() == -1) {
            return false;
        } else if (hyzk.getCheckedRadioButtonId() == -1) {
            return false;
        } else if (zn.getCheckedRadioButtonId() == -1) {
            return false;
        } else if (xg.getCheckedRadioButtonId() == -1) {
            return false;
        } else if (gxy.getCheckedRadioButtonId() == -1) {
            return false;
        } else if (tnb.getCheckedRadioButtonId() == -1) {
            return false;
        } else if (gxb.getCheckedRadioButtonId() == -1) {
            return false;
        } else if (gxz.getCheckedRadioButtonId() == -1) {
            return false;
        } else if (px.getCheckedRadioButtonId() == -1) {
            return false;
        } else if (nxg.getCheckedRadioButtonId() == -1) {
            return false;
        } else if (nws.getCheckedRadioButtonId() == -1) {
            return false;
        } else if (lngr.getCheckedRadioButtonId() == -1) {
            return false;
        } else if (ymgr.getCheckedRadioButtonId() == -1) {
            return false;
        } else if (jzb.getCheckedRadioButtonId() == -1) {
            return false;
        } else if (yyz.getCheckedRadioButtonId() == -1) {
            return false;
        } else if (pjs.getCheckedRadioButtonId() == -1) {
            return false;
        } else {
            return true;
        }
    }

    public void save(View view) {
        if (judge()) {
            Gauge gauge = new Gauge();
            gauge.setName(name.getText().toString());
            gauge.setNl(nl.getText().toString());
            gauge.setSjynx(sjynx.getText().toString());
            gauge.setSg(sg.getText().toString());
            gauge.setTz(tz.getText().toString());
            gauge.setTouw(touw.getText().toString());
            gauge.setYw(yw.getText().toString());
            gauge.setTunw(tunw.getText().toString());
            gauge.setSsy(ssy.getText().toString());
            gauge.setSzy(szy.getText().toString());
            gauge.setXl(xl.getText().toString());
            gauge.setMmse_df(mmsedf.getText().toString());
            gauge.setGds_df(gdsdf.getText().toString());
            gauge.setAdl_df(adldf.getText().toString());
            gauge.setNpi_df(npidf.getText().toString());
            gauge.setVft_df(vftdf.getText().toString());
            gauge.setCdr_df(cdrdf.getText().toString());

            RadioButton xb0 = (RadioButton) findViewById(xb.getCheckedRadioButtonId());
            gauge.setXb(xb0.getText().toString());
            RadioButton ls0 = (RadioButton) findViewById(ls.getCheckedRadioButtonId());
            gauge.setLs(ls0.getText().toString());
            RadioButton zy0 = (RadioButton) findViewById(zy.getCheckedRadioButtonId());
            gauge.setZy(zy0.getText().toString());
            RadioButton ysr0 = (RadioButton) findViewById(ysr.getCheckedRadioButtonId());
            gauge.setYsr(ysr0.getText().toString());
            RadioButton hyzk0 = (RadioButton) findViewById(hyzk.getCheckedRadioButtonId());
            gauge.setHyzk(hyzk0.getText().toString());
            RadioButton zn0 = (RadioButton) findViewById(zn.getCheckedRadioButtonId());
            gauge.setZn(zn0.getText().toString());
            RadioButton xg0 = (RadioButton) findViewById(xg.getCheckedRadioButtonId());
            gauge.setXg(xg0.getText().toString());
            RadioButton gxy0 = (RadioButton) findViewById(gxy.getCheckedRadioButtonId());
            gauge.setGxy(gxy0.getText().toString());
            RadioButton tnb0 = (RadioButton) findViewById(tnb.getCheckedRadioButtonId());
            gauge.setTnb(tnb0.getText().toString());
            RadioButton gxb0 = (RadioButton) findViewById(gxb.getCheckedRadioButtonId());
            gauge.setGxb(gxb0.getText().toString());
            RadioButton gxz0 = (RadioButton) findViewById(gxz.getCheckedRadioButtonId());
            gauge.setGxz(gxz0.getText().toString());
            RadioButton px0 = (RadioButton) findViewById(px.getCheckedRadioButtonId());
            gauge.setPx(px0.getText().toString());
            RadioButton nxg0 = (RadioButton) findViewById(nxg.getCheckedRadioButtonId());
            gauge.setNxg(nxg0.getText().toString());
            RadioButton nws0 = (RadioButton) findViewById(nws.getCheckedRadioButtonId());
            gauge.setNws(nws0.getText().toString());
            RadioButton lngr0 = (RadioButton) findViewById(lngr.getCheckedRadioButtonId());
            gauge.setLngr(lngr0.getText().toString());
            RadioButton ymgr0 = (RadioButton) findViewById(ymgr.getCheckedRadioButtonId());
            gauge.setYmgr(ymgr0.getText().toString());
            RadioButton jzb0 = (RadioButton) findViewById(jzb.getCheckedRadioButtonId());
            gauge.setJzb(jzb0.getText().toString());
            RadioButton yyz0 = (RadioButton) findViewById(yyz.getCheckedRadioButtonId());
            gauge.setYyz(yyz0.getText().toString());
            RadioButton pjs0 = (RadioButton) findViewById(pjs.getCheckedRadioButtonId());
            gauge.setPjs(pjs0.getText().toString());

            if (addlog == 0) {
                gauge.setUserId(userId);
                gauge.save();
            } else {
                gauge.update(gaugeId);
            }

            setResult(100);
            finish();
        } else {
            Toasty.error(this, "信息填写或选择不完整，请检查！", Toast.LENGTH_LONG, true).show();
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.gauge_add;
    }
}
