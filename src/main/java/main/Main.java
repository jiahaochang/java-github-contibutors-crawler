package main;

import Util.FileUtil;
import Util.PathConf;
import Util.TokenUtil;
import com.alibaba.fastjson.JSON;
import domain.Project;
import domain.User;
import service.Contributor;

import java.util.List;

/**
 * @Author: Jiahao.Zhang
 * @Date: 2019-04-05 16:21
 * @E-mail jhcheung@mail.ustc.edu.cn
 */

public class Main {
    public static void main(String args[]){
        List<String> baiduAccountList = FileUtil.readCsv(PathConf.BaiduAccountFile);
        List<String> tencentAccountList = FileUtil.readCsv(PathConf.TencentAccountFile);
        List<String> alibabaAccountList = FileUtil.readCsv(PathConf.AlibabaAccountFile);
        Contributor contributorService = new Contributor();

        getProjectListByAccountList(baiduAccountList, contributorService);

        getProjectListByAccountList(tencentAccountList, contributorService);

        getProjectListByAccountList(alibabaAccountList, contributorService);

        /*List<String> urls = contributorService.getUrlsByContributorUrl("https://api.github.com/repos/ApolloAuto/apollo-kernel/contributors");
        System.out.println(urls);*/

    }

    public static void writeProjectListToFile(List<Project> projectList, Contributor contributorService){
        projectList.stream().forEach(project -> {
            //根据project里面contributor_url获取contributor列表，然后获取每个contributor的url
            List<String> urls = contributorService.getUrlsByContributorUrl(project.getContributors_url());
            System.out.println("有 "+urls.size()+" 个contributor");
            List<User> userList = contributorService.getUserListByUrls(urls);
            project.setContributorList(userList);
            String jsonStr = JSON.toJSONString(project);
            System.out.println(jsonStr);
            FileUtil.writeToFile(PathConf.OutPutFile, jsonStr);
        });
    }

    public static void getProjectListByAccountList(List<String> accountList, Contributor contributorService){
        for (int i=1;i<accountList.size();i++){
            String tempAccount = accountList.get(i);
            System.out.println("当前账号 ： "+tempAccount);
            List<Project> projectList = contributorService.getProjectListByAccount(tempAccount);
            System.out.println("当前账号下的project的数量 = "+projectList.size());
            writeProjectListToFile(projectList, contributorService);
        }
    }

}
