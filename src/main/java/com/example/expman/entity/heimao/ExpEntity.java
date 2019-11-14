package com.example.expman.entity.heimao;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.*;

/**
 * @ClassName ExpEntity
 * @Description TODO
 * @Author 程方园
 * @Date 2019/11/1 15:29
 * @Version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpEntity extends BaseRowModel {
    @ExcelProperty(value = "包裹查詢號碼",index=0)
    private String num;
    @ExcelProperty(value = "目前狀態",index=1)
    private String state;
    @ExcelProperty(value = "資料登入時間",index=2)
    private String date;
    @ExcelProperty(value = "負責營業所",index=3)
    private String place;
    @ExcelProperty(value = "备注")
    private String remake;

}
