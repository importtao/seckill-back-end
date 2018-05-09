package me.importtao.seckillbackend.util;

import me.importtao.seckillbackend.dao.UserSequenceMapper;
import me.importtao.seckillbackend.model.UserSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Package me.importtao.seckill.util
 * Class GeneratorUserId
 * Description: 生成ID
 *
 * @author importtao
 * date 2018/1/18 14:18
 * @version V1.0
 */
@Transactional(rollbackFor = Exception.class)
@Service
@Scope("singleton")
public  class GeneratorId {
    @Autowired
    private UserSequenceMapper userSequenceMapper;
    /**
     * 用户编号：编号：共18位 1-8:注册日期 9:用户类型 0：普通用户 1：商户 10-18:用户流水号'
     */
    private static int type = 0;

    synchronized public  String getUserId() throws Exception{
        type = 0;
        return getId();
    }

    public  String getSellerId ()throws Exception{
        type = 1;
        return getId();
    }

    public  String getGoodsId ()throws Exception{
        type = 2;
        return getId();
    }

    public  String getId() throws Exception {
        //初始序列号
        String sequence = "000000001";
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        String id = sdf.format(date)+type;
        UserSequence userSequence = userSequenceMapper.selectByPrimaryKey(id);
        if(userSequence == null){
            UserSequence userSequenceNew = new UserSequence();
            userSequenceNew.setMaxSequence(sequence);
            userSequenceNew.setTodayDate(date);
            userSequenceNew.setStatus(type);
            userSequenceNew.setId(id);
            userSequenceNew.setUpdateTime(date);
            userSequenceMapper.insert(userSequenceNew);
        }else{
            sequence = userSequence.getMaxSequence();
            String limitSequence = "999999999";
            if(sequence.equals(limitSequence)){
                throw new Exception();
            }
            Integer sInt = Integer.parseInt(sequence);
            sInt++;
            char [] c = new char[9];
            char [] r = String.valueOf(sInt).toCharArray();
            int j=r.length;
            for(int i=(c.length-1);i>=0;i--){
                if(j>0){
                    j--;
                    c[i] =r[j];

                }else{
                    c[i] ='0';
                }
            }
            sequence = new String(c);
            userSequence.setMaxSequence(sequence);
            userSequence.setUpdateTime(new Date());
            userSequence.setStatus(type);
            userSequence.setId(id);
            userSequenceMapper.updateByPrimaryKey(userSequence);
        }
        StringBuilder userId = new StringBuilder();
        String dateStr = sdf.format(date);
        userId.append(dateStr);
        userId.append(type);
        userId.append(sequence);
        return userId.toString();
    }
}
