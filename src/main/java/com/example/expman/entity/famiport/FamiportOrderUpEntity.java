package com.example.expman.entity.famiport;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.*;

/**
 * @ClassName FamiportOrderUpEntity
 * @Description 全家上传实体类
 * @Author 程方园
 * @Date 2019/11/12 11:39
 * @Version 1.0
 */

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FamiportOrderUpEntity extends BaseRowModel {
    @ExcelProperty(value = "快递号",index=0)
    private String expNum;
}
