package service.impl;

import Util.HttpHelper;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import service.ContributorService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Jiahao.Zhang
 * @Date: 2019-04-05 17:03
 * @E-mail jhcheung@mail.ustc.edu.cn
 */

public class ContributorServiceImpl implements ContributorService {
    @Override
    public List<String> getProjectListByAccount(String account, String token) {
        List<String> result = new ArrayList<>();
        int j = 1;
        //boolean flag = true;
        while (true){
            String url = "https://api.github.com/users/"+account+"/repos?page=" + j + "&per_page=100";
            Map<String, String> header = new HashMap<>();
            header.put("Authorization",token);
            String repoInfo = "";
            try {
                repoInfo = HttpHelper.doGet(url, header);
                System.out.println(repoInfo);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //System.out.println(repoInfo);
            JSONArray repoJsonArray = JSONArray.parseArray(repoInfo);
            if (repoJsonArray.size()==0){
                break;
            }else {
                for (int i=0;i<repoJsonArray.size();i++){
                    JSONObject tempProject = repoJsonArray.getJSONObject(i);
                    String contributorUrl = tempProject.getString("contributors_url");
                    result.add(contributorUrl);
                }
            }
            j++;
        }
        return result;
    }
}
