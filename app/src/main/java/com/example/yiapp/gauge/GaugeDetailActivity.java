package com.example.yiapp.gauge;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.yiapp.R;
import com.example.yiapp.base.BaseActivity;
import com.example.yiapp.data.Gauge;

import org.litepal.LitePal;

public class GaugeDetailActivity extends BaseActivity {

    TextView name, nl, sjynx, sg, tz, touw, yw, tunw, ssy, szy, xl, mmsedf, gdsdf, adldf, npidf, vftdf, cdrdf,
            xb, ls, zy, ysr, hyzk, zn, xg, gxy, tnb, gxb, gxz, px, nxg, nws, lngr, ymgr, jzb, yyz, pjs;
    int gaugeId;

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
        gaugeId = bundle.getInt("gaugeid");
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
        xb.setText(gauge.getXb());
        ls.setText(gauge.getLs());
        zy.setText(gauge.getZy());
        ysr.setText(gauge.getYsr());
        hyzk.setText(gauge.getHyzk());
        zn.setText(gauge.getZn());
        xg.setText(gauge.getXg());
        gxy.setText(gauge.getGxy());
        tnb.setText(gauge.getTnb());
        gxb.setText(gauge.getGxb());
        gxz.setText(gauge.getGxz());
        px.setText(gauge.getPx());
        nxg.setText(gauge.getNxg());
        nws.setText(gauge.getNws());
        lngr.setText(gauge.getLngr());
        ymgr.setText(gauge.getYmgr());
        jzb.setText(gauge.getJzb());
        yyz.setText(gauge.getYyz());
        pjs.setText(gauge.getPjs());
    }

    @Override
    protected void initViews() {
        init();
        setView();
    }

    public void back(View v){
        finish();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.gauge_detail;
    }
}
