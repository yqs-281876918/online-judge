package org.upc.oj.bank.dto;

import lombok.Data;

import java.util.List;

@Data
public class TagRequestData {
    private int qid;
    private List<Integer> tagIds;
}
