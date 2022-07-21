package org.upc.oj.bank.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.upc.oj.bank.po.Question;

/**
 * 包装过的Question
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QuestionWrapper extends Question {
    private boolean solved;
    private double passRate;
}