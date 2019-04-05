package service;

import java.util.List;

/**
 * @Author: Jiahao.Zhang
 * @Date: 2019-04-05 17:02
 * @E-mail jhcheung@mail.ustc.edu.cn
 */

public interface ContributorService {
    List<String> getProjectListByAccount(String account, String token);
}
