package org.upc.oj.bank.po;

import lombok.Data;

@Data
public class Question {
    private Integer id;
    private String title;
    private String content;
    private Integer difficulty;
    private Integer io_count;
    private Integer timeout;
    private Integer memory_limit;
    private boolean deleted;
}
