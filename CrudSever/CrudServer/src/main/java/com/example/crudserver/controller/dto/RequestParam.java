package com.example.crudserver.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestParam {
    public Integer pageNum;
    public Integer pageSize;
    public String keyword;
}
