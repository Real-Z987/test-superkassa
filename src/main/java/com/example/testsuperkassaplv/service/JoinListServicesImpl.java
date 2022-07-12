package com.example.testsuperkassaplv.service;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service("joinListServices")
public class JoinListServicesImpl implements JoinListServices {

    @Override
    public List<String> joinTwoLists(List<String> list1, List<String> list2) {
        List<String> joinedString = new ArrayList<>();
        int maxLength = Math.max(list1.size(), list2.size());
        for (int i = 0; i < maxLength; i++) {
            /* the task does not indicate what the result should be if the size is different.
               Implemented padding to the same value with nulls. Can be replaced by returning an empty list
             */
            if (i >= list1.size()) {
                joinedString.add(list2.get(i));
            } else if (i >= list2.size()) {
                joinedString.add(list1.get(i));
            } else if (ObjectUtils.isEmpty(list1.get(i))) {
                joinedString.add(list2.get(i));
            } else if (ObjectUtils.isEmpty(list2.get(i))) {
                joinedString.add(list1.get(i));
            } else {
                return Collections.emptyList();
            }
        }

        return joinedString;
    }
}
