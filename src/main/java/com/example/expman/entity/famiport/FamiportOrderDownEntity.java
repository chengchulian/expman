package com.example.expman.entity.famiport;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.*;

/**
 * @ClassName FamiportOrderDownEntity
 * @Description TODO
 * @Author 程方园
 * @Date 2019/11/12 11:42
 * @Version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FamiportOrderDownEntity extends BaseRowModel {

    @ExcelProperty(value = "快递号",index = 0)
    private String expNum;
    @ExcelProperty(value = "订单号",index = 1)
    private String orderNum;
    @ExcelProperty(value = "取货截至日期",index = 2)
    private String date;
    @ExcelProperty(value = "商品状态",index = 3)
    private String state;
    @ExcelProperty(value = "备注")
    private String remake;

}
