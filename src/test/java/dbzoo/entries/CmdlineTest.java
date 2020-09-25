package dbzoo.entries;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CmdlineTest {

    @Test
    void shouldListAnimals () {
        Cmdline cmdline = new Cmdline();
        cmdline.listAnimals();
    }

}