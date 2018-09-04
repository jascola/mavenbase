package com.jascola.entity;

import java.sql.Time;


/**
 * 里番合集
 */
public class AcgContentEntity {
    /**
     * 合集名称
     */
    private String acgcontentname;
    /**
     * 合集id
     */
    private Integer acgctid;
    /**
     * 日期
     */
    private Time releasetime;
    /**
     * 磁力（可能没有）
     */
    private String magnet;

    public String getAcgcontentname() {
        return acgcontentname;
    }

    public void setAcgcontentname(String acgcontentname) {
        this.acgcontentname = acgcontentname;
    }

    public Time getReleasetime() {
        return releasetime;
    }

    public void setReleasetime(Time releasetime) {
        this.releasetime = releasetime;
    }

    public String getMagnet() {
        return magnet;
    }

    public void setMagnet(String magnet) {
        this.magnet = magnet;
    }
}
