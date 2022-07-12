package com.example.testsuperkassaplv.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;

class JoinListServicesImplTest {
    private final JoinListServicesImpl joinListServices;

    JoinListServicesImplTest() {
        this.joinListServices = new JoinListServicesImpl();
    }

    @Test
    public void testJoinStringsCase1() {
        List<String> list1 = Arrays.asList("a1", null, "a3", null);
        List<String> list2 = Arrays.asList(null, "b2", null, "b4");

        List<String> joinedList = joinListServices.joinTwoLists(list1, list2);
        Assertions.assertFalse(CollectionUtils.isEmpty(joinedList));

        List<String> resultList = Arrays.asList("a1", "b2", "a3", "b4");
        Assertions.assertEquals(resultList, joinedList);
    }

    @Test
    public void testJoinStringsCase2() {
        List<String> list1 = Arrays.asList("a1", null, "a3", null);
        List<String> list2 = Arrays.asList(null, null, null, "b4");

        List<String> joinedList = joinListServices.joinTwoLists(list1, list2);
        Assertions.assertFalse(CollectionUtils.isEmpty(joinedList));

        List<String> resultList = Arrays.asList("a1", null, "a3", "b4");
        Assertions.assertEquals(resultList, joinedList);
    }

    @Test
    public void testJoinStringsCase3() {
        List<String> list1 = Arrays.asList("a1", null, "a3");
        List<String> list2 = Arrays.asList(null, "b2", null, "b4");

        List<String> joinedList = joinListServices.joinTwoLists(list1, list2);
        Assertions.assertFalse(CollectionUtils.isEmpty(joinedList));

        List<String> resultList = Arrays.asList("a1", "b2", "a3", "b4");
        Assertions.assertEquals(resultList, joinedList);
    }


    @Test
    public void testJoinStringsCase4() {
        List<String> list1 = Arrays.asList("a1", null, "a3", null);
        List<String> list2 = Arrays.asList("b1", "b2", null, "b4");

        List<String> joinedList = joinListServices.joinTwoLists(list1, list2);
        Assertions.assertTrue(CollectionUtils.isEmpty(joinedList));
    }

}