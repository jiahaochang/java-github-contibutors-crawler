package domain;

import java.util.Date;
import java.util.List;

/**
 * @Author: Jiahao Zhang
 * @Date: 2019/4/6 15:40
 * @Email: jhcheung@mail.ustc.edu.cn
 * @Version 1.0
 */
public class Project {
    private Long id;
    private String name;
    private String full_name;
    private String description;
    private Boolean fork;
    private Long size;
    private Long stargazers_count;
    private String language;
    private Long forks_count;
    private Long open_issues;
    private Date created_at;
    private Date updated_at;
    private Date pushed_at;
    private String contributors_url;
    private List<User> contributorList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getFork() {
        return fork;
    }

    public void setFork(Boolean fork) {
        this.fork = fork;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Long getStargazers_count() {
        return stargazers_count;
    }

    public void setStargazers_count(Long stargazers_count) {
        this.stargazers_count = stargazers_count;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Long getForks_count() {
        return forks_count;
    }

    public void setForks_count(Long forks_count) {
        this.forks_count = forks_count;
    }

    public Long getOpen_issues() {
        return open_issues;
    }

    public void setOpen_issues(Long open_issues) {
        this.open_issues = open_issues;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public Date getPushed_at() {
        return pushed_at;
    }

    public void setPushed_at(Date pushed_at) {
        this.pushed_at = pushed_at;
    }

    public String getContributors_url() {
        return contributors_url;
    }

    public void setContributors_url(String contributors_url) {
        this.contributors_url = contributors_url;
    }

    public List<User> getContributorList() {
        return contributorList;
    }

    public void setContributorList(List<User> contributorList) {
        this.contributorList = contributorList;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", full_name='" + full_name + '\'' +
                ", description='" + description + '\'' +
                ", fork=" + fork +
                ", size=" + size +
                ", stargazers_count=" + stargazers_count +
                ", language='" + language + '\'' +
                ", forks_count=" + forks_count +
                ", open_issues=" + open_issues +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                ", pushed_at=" + pushed_at +
                ", contributors_url='" + contributors_url + '\'' +
                ", contributorList=" + contributorList +
                '}';
    }
}
