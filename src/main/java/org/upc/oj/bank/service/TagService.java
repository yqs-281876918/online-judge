package org.upc.oj.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.upc.oj.MainApplication;
import org.upc.oj.bank.dao.TagMapper;

import java.util.Scanner;

@Service
public class TagService {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a=scanner.nextInt();
        int b=scanner.nextInt();
        System.out.println(Math.max(a,b));
    }
    @Autowired
    private TagMapper tagMapper;

    public String createTag(String title){
        try {
            tagMapper.insertTag(title);
        }catch (Exception e){
            return e.getCause().getMessage();
        }
        return null;
    }
}
