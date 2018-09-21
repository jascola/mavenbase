package com.jascola.entity;

public class CollectionEntity {
    private String phone;

    private String id;
    /**
     * 相册名
     */
    private String picname;
    /**
     * 作者名
     */
    private String authorname;
    /**
     * 标签
     */
    private String tag;
    /**
     * 物理路径
     */
    private String realdir;
    /**
     * 静态资源代理地址
     */
    private String virtualdir;
    /**
     * 静态服务代理地址
     */
    private String indexpic;
    /**
     * 物理目录路径
     */
    private String indexrealdir;
    /**
     * 相片个数
     */
    private Integer counts;

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPicname() {
        return picname;
    }

    public void setPicname(String picname) {
        this.picname = picname;
    }

    public String getAuthorname() {
        return authorname;
    }

    public void setAuthorname(String authorname) {
        this.authorname = authorname;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getRealdir() {
        return realdir;
    }

    public void setRealdir(String realdir) {
        this.realdir = realdir;
    }

    public String getVirtualdir() {
        return virtualdir;
    }

    public void setVirtualdir(String virtualdir) {
        this.virtualdir = virtualdir;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
