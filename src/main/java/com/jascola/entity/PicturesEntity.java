package com.jascola.entity;

public class PicturesEntity {
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
     * 物理目录路径
     */
    private String indexpic;
    /**
     * 静态服务代理地址
     */
    private String indexrealdir;
    /**
     * 相片个数
     */
    private Integer count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
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
}
