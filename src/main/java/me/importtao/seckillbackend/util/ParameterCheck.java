package me.importtao.seckillbackend.util;

/**
 * Package me.importtao.usersystem.util
 * Class ParameterCheck
 * Description: TODO
 *
 * @author importtao
 * date 2018/2/13 14:58
 * @version V1.0
 */
public class ParameterCheck {

    /**
     * @description
     * @author importtao
     * @date 2018/2/13 14:59
     * @param param ��ҪУ��Ĳ���
     * @param regularExpress У�����
     * @return
     */
    public boolean check(String param,String regularExpress){
        if(param.equals("")||param == null){
            return false;
        }
        return param.matches(regularExpress);
    }
}
