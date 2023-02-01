package com.ossez.wechat.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Email Testing
 *
 * @author YuCheng
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Slf4j
class DataTest {

    @Test
    public void testStartAndEndDateTime() {

        List<Integer> reqIds = Arrays.asList(1, 2);
        List<Integer> reqs = Arrays.asList(1);
        Map<Integer, Boolean> map1 = reqIds.stream().collect(Collectors.toMap(Function.identity(), item -> reqs.contains(item)));
        Map<Integer, Boolean> map2 = reqIds.stream().collect(Collectors.toMap(Function.identity(), reqs::contains));

        log.debug("Map Size {}",11+20);

        log.debug("Map Size {}", map2);


        List<Integer> shiftIds = Arrays.asList(1, 2);
        Map<Integer, Boolean> shiftIdMaps = new HashMap<Integer, Boolean>();
        shiftIdMaps.put(1, Boolean.TRUE);
        List<Integer> reponse = shiftIds.stream().filter(item -> shiftIdMaps.get(item) == Boolean.TRUE).collect(Collectors.toList());
        log.debug("Map Size {}", reponse);

        log.debug("Map Size {}",map2);
    }


}


