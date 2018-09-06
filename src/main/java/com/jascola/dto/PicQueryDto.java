package com.jascola.dto;

public class PicQueryDto extends PageinfoDto {
    private Integer id;

    private String picname;

    private String authorname;

    private String tag;

    private String realdir;

    private String virtualdir;

    private String indexpic;

    private String indexrealdir;

    private Integer counts;

    private String param;

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }
}
