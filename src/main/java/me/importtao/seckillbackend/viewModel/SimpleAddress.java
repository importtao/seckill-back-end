package me.importtao.seckillbackend.viewModel;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Package me.importtao.seckillbackend.viewModel
 * Class SimpleAddress
 * Description: TODO
 *
 * @author importtao
 * date 2018/6/1 10:46
 * @version V1.0
 */
@Component
public class SimpleAddress implements Serializable{
    private static final long serialVersionUID = 1L;
    private Long id;
    private String simpleAddress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSimpleAddress() {
        return simpleAddress;
    }

    public void setSimpleAddress(String simpleAddress) {
        this.simpleAddress = simpleAddress;
    }
}
