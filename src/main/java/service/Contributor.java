package service;

import Util.HttpHelper;
import Util.TokenUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import domain.Project;
import domain.User;
import org.apache.tomcat.util.buf.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Jiahao Zhang
 * @Date: 2019/4/6 15:38
 * @Email: jhcheung@mail.ustc.edu.cn
 * @Version 1.0
 */
public class Contributor {
    public List<Project> getProjectListByAccount(String account) {

        List<Project> result = new ArrayList<>();
        int j = 1;
        //boolean flag = true;
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String token = TokenUtil.getUsableToken();
            String url = "https://api.github.com/users/" + account + "/repos?page=" + j + "&per_page=100";
            Map<String, String> header = new HashMap<>();
            header.put("Authorization", token);
            String repoInfo = "";
            try {
                repoInfo = HttpHelper.doGet(url, header);
                System.out.println("获取project的url = "+url);
                System.out.println("获取到project信息 = "+repoInfo);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //System.out.println(repoInfo);
            if (repoInfo == null || repoInfo.equals("")){
                continue;
            }
            JSONArray repoJsonArray = JSONArray.parseArray(repoInfo);
            if (repoJsonArray.size() == 0) {
                break;
            } else {
                for (int i = 0; i < repoJsonArray.size(); i++) {
                    JSONObject tempProject = repoJsonArray.getJSONObject(i);
                    Project project = JSON.parseObject(tempProject.toJSONString(), Project.class);
                    result.add(project);
                }
            }
            j++;
        }
        return result;
    }

    public List<User> getUserListByUrls(List<String> urls){
        List<User> userList = new ArrayList<>();
        urls.stream().forEach(url->{
            boolean flag = true;
            while (flag){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String token = TokenUtil.getUsableToken();
                Map<String, String> header = new HashMap<>();
                header.put("Authorization", token);
                String repoInfo = "";
                try {
                    repoInfo = HttpHelper.doGet(url, header);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (repoInfo==null || repoInfo.equals("")){
                    continue;
                }

                User user = JSON.parseObject(repoInfo, User.class);
                String location = user.getLocation();
                if (location==null){
                    user.setLocation("");
                }
                String email = user.getEmail();
                if (email == null){
                    user.setEmail("");
                }
                userList.add(user);
                flag = false;

            }

        });
        return userList;
    }

    public List<String> getUrlsByContributorUrl(String contributor_url){
        List<String> urlList = new ArrayList<>();
        while (true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String token = TokenUtil.getUsableToken();
            Map<String, String> header = new HashMap<>();
            header.put("Authorization", token);
            String repoInfo = "";
            try {
                repoInfo = HttpHelper.doGet(contributor_url, header);
                System.out.println(contributor_url);
                System.out.println("根据contributor_url请求的返回结果 = "+repoInfo);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(repoInfo);
            if (repoInfo!=null && !repoInfo.equals("")){
                List<domain.Contributor> contributors = JSON.parseArray(repoInfo, domain.Contributor.class);
                System.out.println("contributor的数量 = "+contributors.size());
                contributors.stream().forEach(contributor -> {
                    urlList.add(contributor.getUrl());
                });
                return urlList;
            }
        }

    }

}
