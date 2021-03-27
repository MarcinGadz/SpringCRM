import com.marcingadz.entity.Customer;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class CustomerTest {

    @Test
    public void constructorsTest() {
        Customer c = new Customer();
        assertEquals(c.getEmail(), null);
        assertEquals(c.getId(), 0);
        assertEquals(c.getFirstName(), null);
        assertEquals(c.getLastName(), null);
        String fname = "abc";
        String lname = "bbb";
        String email = "qwerty";
        c = new Customer(fname, lname, email);
        assertEquals(c.getEmail(), email);
        assertEquals(c.getFirstName(), fname);
        assertEquals(c.getLastName(), lname);
    }
}
