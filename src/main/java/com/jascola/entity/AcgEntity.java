package com.jascola.entity;

import java.io.Serializable;

/**
 * lifan
 */
public class AcgEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 合集名称(属于哪个合集)
     */
    private Integer acgctid;
    /**
     * lifan 名称
     */
    private String acgname;
    /**
     * 发出日期
     */
    private String releasetime;
    /**
     * 磁力链接
     */
    private String magnet;
    /**
     * 静态封面地址
     */
    private String indexpic;
    /**
     * 物理地址
     */
    private String indexrealdir;
    /**
     * 发行商
     */
    private String publisher;

    public Integer getAcgctid() {
        return acgctid;
    }

    public void setAcgctid(Integer acgctid) {
        this.acgctid = acgctid;
    }

    public String getAcgname() {
        return acgname;
    }

    public void setAcgname(String acgname) {
        this.acgname = acgname;
    }

    public String getReleasetime() {
        return releasetime;
    }

    public void setReleasetime(String releasetime) {
        this.releasetime = releasetime;
    }

    public String getMagnet() {
        return magnet;
    }

    public void setMagnet(String magnet) {
        this.magnet = magnet;
    }

    public String getIndexpic() {
        return indexpic;
    }

    public void setIndexpic(String indexpic) {
        this.indexpic = indexpic;
    }

    public String getIndexrealdir() {
        return indexrealdir;
    }

    public void setIndexrealdir(String indexrealdir) {
        this.indexrealdir = indexrealdir;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
