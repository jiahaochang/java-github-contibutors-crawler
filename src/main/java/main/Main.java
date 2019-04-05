package main;

import Util.FileUtil;
import Util.PathConf;
import Util.TokenUtil;

import java.util.List;

/**
 * @Author: Jiahao.Zhang
 * @Date: 2019-04-05 16:21
 * @E-mail jhcheung@mail.ustc.edu.cn
 */

public class Main {
    public static void main(String args[]){
        TokenUtil.getUsableToken();

        List<String> tencentAccountList = FileUtil.readCsv(PathConf.TencentAccountFile);
    }
}
