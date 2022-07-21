package org.upc.oj.bank.dto;

import lombok.Data;

@Data
public class QuestionList {
    private Integer id;
    private String title;
    private Integer difficulty;
    private Integer nb_submit;
    private String solved;
    private double passRate;
}
