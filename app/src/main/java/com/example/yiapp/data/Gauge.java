package com.example.yiapp.data;

import org.litepal.crud.LitePalSupport;

public class Gauge extends LitePalSupport {

    private int id;
    private String name;
    private String xb;//性别
    private String nl;//年龄
    private String sjynx;//受教育年限
    private String sg;//身高
    private String tz;//体重
    private String touw;//头围
    private String yw;//腰围
    private String tunw;//臀围
    private String ssy;//收缩压
    private String szy;//舒张压
    private String xl;//心率
    private String ls;//利手
    private String zy;//职业
    private String ysr;//月收入
    private String hyzk;//婚姻状况
    private String zn;//子女
    private String xg;//性格
    private String gxy;//高血压
    private String tnb;//糖尿病
    private String gxb;//冠心病
    private String gxz;//高血脂
    private String px;//贫血
    private String nxg;//脑血管
    private String nws;//脑外伤
    private String lngr;//颅内感染
    private String ymgr;//颜面感染
    private String jzb;//颈椎病史
    private String yyz;//忧郁症
    private String pjs;//帕金森
    private String mmse_df;//简易精神状态量表得分
    private String gds_df;//老年抑郁量表得分
    private String adl_df;//日常生活能力量表得分
    private String npi_df;//神经精神量表得分
    private String vft_df;//词语流畅性得分
    private String cdr_df;//临床痴呆评定表得分

    private int userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getXb() {
        return xb;
    }

    public void setXb(String xb) {
        this.xb = xb;
    }

    public String getNl() {
        return nl;
    }

    public void setNl(String nl) {
        this.nl = nl;
    }

    public String getSjynx() {
        return sjynx;
    }

    public void setSjynx(String sjynx) {
        this.sjynx = sjynx;
    }

    public String getSg() {
        return sg;
    }

    public void setSg(String sg) {
        this.sg = sg;
    }

    public String getTz() {
        return tz;
    }

    public void setTz(String tz) {
        this.tz = tz;
    }

    public String getTouw() {
        return touw;
    }

    public void setTouw(String touw) {
        this.touw = touw;
    }

    public String getYw() {
        return yw;
    }

    public void setYw(String yw) {
        this.yw = yw;
    }

    public String getTunw() {
        return tunw;
    }

    public void setTunw(String tunw) {
        this.tunw = tunw;
    }

    public String getSsy() {
        return ssy;
    }

    public void setSsy(String ssy) {
        this.ssy = ssy;
    }

    public String getSzy() {
        return szy;
    }

    public void setSzy(String szy) {
        this.szy = szy;
    }

    public String getXl() {
        return xl;
    }

    public void setXl(String xl) {
        this.xl = xl;
    }

    public String getLs() {
        return ls;
    }

    public void setLs(String ls) {
        this.ls = ls;
    }

    public String getZy() {
        return zy;
    }

    public void setZy(String zy) {
        this.zy = zy;
    }

    public String getYsr() {
        return ysr;
    }

    public void setYsr(String ysr) {
        this.ysr = ysr;
    }

    public String getHyzk() {
        return hyzk;
    }

    public void setHyzk(String hyzk) {
        this.hyzk = hyzk;
    }

    public String getZn() {
        return zn;
    }

    public void setZn(String zn) {
        this.zn = zn;
    }

    public String getXg() {
        return xg;
    }

    public void setXg(String xg) {
        this.xg = xg;
    }

    public String getGxy() {
        return gxy;
    }

    public void setGxy(String gxy) {
        this.gxy = gxy;
    }

    public String getTnb() {
        return tnb;
    }

    public void setTnb(String tnb) {
        this.tnb = tnb;
    }

    public String getGxb() {
        return gxb;
    }

    public void setGxb(String gxb) {
        this.gxb = gxb;
    }

    public String getGxz() {
        return gxz;
    }

    public void setGxz(String gxz) {
        this.gxz = gxz;
    }

    public String getPx() {
        return px;
    }

    public void setPx(String px) {
        this.px = px;
    }

    public String getNxg() {
        return nxg;
    }

    public void setNxg(String nxg) {
        this.nxg = nxg;
    }

    public String getNws() {
        return nws;
    }

    public void setNws(String nws) {
        this.nws = nws;
    }

    public String getLngr() {
        return lngr;
    }

    public void setLngr(String lngr) {
        this.lngr = lngr;
    }

    public String getYmgr() {
        return ymgr;
    }

    public void setYmgr(String ymgr) {
        this.ymgr = ymgr;
    }

    public String getJzb() {
        return jzb;
    }

    public void setJzb(String jzb) {
        this.jzb = jzb;
    }

    public String getYyz() {
        return yyz;
    }

    public void setYyz(String yyz) {
        this.yyz = yyz;
    }

    public String getPjs() {
        return pjs;
    }

    public void setPjs(String pjs) {
        this.pjs = pjs;
    }

    public String getMmse_df() {
        return mmse_df;
    }

    public void setMmse_df(String mmse_df) {
        this.mmse_df = mmse_df;
    }

    public String getGds_df() {
        return gds_df;
    }

    public void setGds_df(String gds_df) {
        this.gds_df = gds_df;
    }

    public String getAdl_df() {
        return adl_df;
    }

    public void setAdl_df(String adl_df) {
        this.adl_df = adl_df;
    }

    public String getNpi_df() {
        return npi_df;
    }

    public void setNpi_df(String npi_df) {
        this.npi_df = npi_df;
    }

    public String getVft_df() {
        return vft_df;
    }

    public void setVft_df(String vft_df) {
        this.vft_df = vft_df;
    }

    public String getCdr_df() {
        return cdr_df;
    }

    public void setCdr_df(String cdr_df) {
        this.cdr_df = cdr_df;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
