package com.example.expman.entity.famiport;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName FamiportResponseList
 * @Description TODO
 * @Author 程方园
 * @Date 2019/11/12 15:25
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FamiportResponseExpInfo{

    String order_no;
    String ec_order_no;
    String ordermessage;
    String send_store_name;
    String rcv_store_name;
    String store_address;
    String store_tel;
    String order_date_02;
    String order_date_02_b;
    String order_date_03;
    String order_date_03_b;
    String order_date_08;
    String order_date_08_b;
    String order_date_09;
    String order_date_09_b;
    String order_date_rtn;
    String order_date_1213;
    String order_date_1213_b;

}
