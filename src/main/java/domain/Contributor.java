package domain;

import java.util.List;

/**
 * @Author: Jiahao Zhang
 * @Date: 2019/4/6 15:49
 * @Email: jhcheung@mail.ustc.edu.cn
 * @Version 1.0
 */
public class Contributor {
    private String login;
    private Long id;
    private String node_id;
    private String avatar_url;
    private String url;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNode_id() {
        return node_id;
    }

    public void setNode_id(String node_id) {
        this.node_id = node_id;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
