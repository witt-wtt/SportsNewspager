package com.teamsports.sportsnewspager.entity;

/**赛事实体类
 * Created by Witt on 2015/3/24.
 */

public class Competition {
    private String id;
    private String title;//标题
    private String customLogo;//标题图片
    private String logo;//主题图片

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCustomLogo() {
        return customLogo;
    }

    public void setCustomLogo(String customLogo) {
        this.customLogo = customLogo;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
