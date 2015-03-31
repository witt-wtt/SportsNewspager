package com.teamsports.sportsnewspager.entity;

/**
 * Created by Administrator on 2015/3/24.
 */
public class ColumnEntity {
    private String id;
    private String pic;
    private String desc;
    private String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *??????
     * @return
     */
    @Override
    public ColumnEntity clone() {
        ColumnEntity entity = new ColumnEntity();
        entity.id = id;
        entity.pic = pic;
        entity.desc = desc;
        entity.title = title;

        return entity;
    }
}
