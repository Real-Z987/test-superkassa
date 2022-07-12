package com.example.testsuperkassaplv.controller;

import com.example.testsuperkassaplv.dto.InputListDto;
import com.example.testsuperkassaplv.service.JoinListServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("join-list")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JoinController {

    private final JoinListServices joinListServices;

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
            var joined = joinListServices.joinTwoLists(currentList, inputList.get(i));
            if (!CollectionUtils.isEmpty(joined)) {
                result.addAll(checkAndJoin(joined, inputList, i));
            }
        }

        return result;
    }

}
