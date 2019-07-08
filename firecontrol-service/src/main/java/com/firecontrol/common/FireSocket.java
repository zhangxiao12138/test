package com.firecontrol.common;

import java.io.Serializable;

/**
 * 与设备机通信的简单socket包定义类
 * Created by mariry on 2019/7/2.
 */
public class FireSocket implements Serializable{
        public int id;
        public int value;
        public String direction;

        public FireSocket(int id, int value, String direction){
            this.id= id;
            this.value= value;
            this.direction= direction;

        }

}
