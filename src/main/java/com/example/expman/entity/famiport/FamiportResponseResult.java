package com.example.expman.entity.famiport;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName FamipostResponseResult
 * @Description TODO
 * @Author 程方园
 * @Date 2019/11/12 15:15
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FamiportResponseResult<T> {
    String errorcode;
    String errormessage;
    List<T> list;
}
