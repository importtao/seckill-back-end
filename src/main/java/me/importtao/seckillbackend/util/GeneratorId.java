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
 * Description: ����ID
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
     * �û���ţ���ţ���18λ 1-8:ע������ 9:�û����� 0����ͨ�û� 1���̻� 10-18:�û���ˮ��'
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
        //��ʼ���к�
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
