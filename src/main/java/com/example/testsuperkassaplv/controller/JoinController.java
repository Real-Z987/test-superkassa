package com.example.testsuperkassaplv.controller;

import com.example.testsuperkassaplv.dto.InputListDto;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("join-list")
public class JoinController {
    @PostMapping
    public List<List<String>> joinList(@RequestBody InputListDto inputListDto) {
        List<List<String>> result = new ArrayList<>();
        List<List<String>> inputList = inputListDto.getTest();

        for (int i = 0; i < inputList.size(); i++) {
            List<String> checkedList = new ArrayList<>(inputList.get(i));
            result.addAll(checkAndJoin(checkedList, inputList, i));
        }
        return result;
    }

    private List<List<String>> checkAndJoin(List<String> currentList, List<List<String>> inputList, int currentIndex) {
        List<List<String>> result = new ArrayList<>();

        if (currentList.stream().noneMatch(ObjectUtils::isEmpty)) {
            result.add(currentList);
            return result;
        }

        for (int i = currentIndex + 1; i < inputList.size(); i++) {
            var joined = joinTwoLists(currentList, inputList.get(i));
            if (!CollectionUtils.isEmpty(joined)) {
                result.addAll(checkAndJoin(joined, inputList, i));
            }
        }

        return result;
    }

    private List<String> joinTwoLists(List<String> list1, List<String> list2) {
        List<String> joinedString = new ArrayList<>();
        int maxLength = Math.max(list1.size(), list2.size());
        for (int i = 0; i < maxLength; i++) {
            /* the task does not indicate what the result should be if the size is different.
               Implemented padding to the same value with nulls. Can be replaced by returning an empty list
             */
            if (list1.size() < maxLength) {
                joinedString.add(list2.get(i));
            } else if (list2.size() < maxLength) {
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
