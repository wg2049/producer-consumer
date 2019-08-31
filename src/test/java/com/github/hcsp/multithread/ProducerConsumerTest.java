package com.github.hcsp.multithread;

import com.github.blindpirate.extensions.CaptureSystemOutput;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

public class ProducerConsumerTest {
    @Test
    @CaptureSystemOutput
    public void test(CaptureSystemOutput.OutputCapture capture) throws Exception {
        String regex =
                IntStream.range(1, 11)
                        .mapToObj(i -> String.format("Producing %d\\s+Consuming %d", i, i))
                        .collect(Collectors.joining("\\s+"));
        capture.expect(Matchers.matchesRegex("(?s).*" + regex + ".*"));

        Boss.main(null);
    }
}
