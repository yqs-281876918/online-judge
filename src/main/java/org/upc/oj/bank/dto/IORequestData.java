package org.upc.oj.bank.dto;

import lombok.Data;
import org.upc.oj.bank.po.InputAndOutput;

import java.util.List;

@Data
public class IORequestData {
    private int qid;
    private List<InputAndOutput> ios;
}
