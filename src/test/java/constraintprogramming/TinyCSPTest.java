package constraintprogramming;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TinyCSPTest {

    @Test
    void makeVariable() {
        TinyCSP csp = new TinyCSP();
        TinyCSP.Variable x = csp.makeVariable(5);
        x.dom.fix(2);
        assertEquals(1, x.dom.size());
        assertTrue(x.dom.isFixed());

    }
}