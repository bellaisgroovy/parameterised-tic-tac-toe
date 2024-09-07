package data;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleListBoolBinaryAdderTest {
    @Test
    public void add_two_binary_numbers() {
        SimpleListBoolBinaryAdder binaryAdder = new SimpleListBoolBinaryAdder();
        List<Boolean> a = List.of(false,true);
        List<Boolean> b = List.of(false,true);

        List<Boolean> expected = List.of(true, false);
        assertEquals(expected, binaryAdder.add(a, b));
    }

    @Test
    public void add_two_binary_numbers_different_no_bits() {
        SimpleListBoolBinaryAdder binaryAdder = new SimpleListBoolBinaryAdder();
        List<Boolean> a = List.of(false,true);
        List<Boolean> b = List.of(true);

        List<Boolean> expected = List.of(true, false);
        assertEquals(expected, binaryAdder.add(a, b));
    }

    @Test
    public void add_two_binary_numbers_2() {
        SimpleListBoolBinaryAdder binaryAdder = new SimpleListBoolBinaryAdder();
        List<Boolean> a = List.of(true,false,false,true,false,true,false,false);
        List<Boolean> b = List.of(true,false,true,false,false,true,false,true);

        List<Boolean> expected = List.of(false,false,true,true,true,false,false,true);
        assertEquals(expected, binaryAdder.add(a, b));
    }

    @Test
    public void returns_list_with_no_bits_matching_longest_entry() {
        SimpleListBoolBinaryAdder binaryAdder = new SimpleListBoolBinaryAdder();
        List<Boolean> a = List.of(false,true);
        List<Boolean> b = List.of(false,false,false,false);
        int expectedNoBits = 4;

        assertEquals(expectedNoBits, binaryAdder.add(a, b).size());
    }

    @Test
    public void add_one() {
        SimpleListBoolBinaryAdder binaryAdder = new SimpleListBoolBinaryAdder();
        List<Boolean> a = List.of(false, true);

        List<Boolean> expected = List.of(true, false);
        assertEquals(expected, binaryAdder.addOne(a));
    }

    @Test
    public void add_one_all_ones_rollover() {
        SimpleListBoolBinaryAdder binaryAdder = new SimpleListBoolBinaryAdder();
        List<Boolean> a = List.of(true, true);

        List<Boolean> expected = List.of(false, false);
        assertEquals(expected, binaryAdder.addOne(a));
    }
}