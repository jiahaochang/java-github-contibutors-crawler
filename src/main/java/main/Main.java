package main;

import Util.FileUtil;
import Util.PathConf;
import Util.TokenUtil;
import service.ContributorService;
import service.impl.ContributorServiceImpl;

import java.util.List;

/**
 * @Author: Jiahao.Zhang
 * @Date: 2019-04-05 16:21
 * @E-mail jhcheung@mail.ustc.edu.cn
 */

public class Main {
    public static void main(String args[]){
        String token = TokenUtil.getUsableToken();
        System.out.println(token);
        //List<String> tencentAccountList = FileUtil.readCsv(PathConf.TencentAccountFile);
        ContributorService contributorService = new ContributorServiceImpl();
        List<String> contributorUrlList = contributorService.getProjectListByAccount("baidu", token);
        System.out.println(contributorUrlList);
    }
}
