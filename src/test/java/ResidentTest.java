import com.bytebuilder.EstateManager.Main;
import com.bytebuilder.EstateManager.data.models.Resident;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {Main.class})
public class ResidentTest {

    @Test
    public void testThatNewResidentCanBeCreated() {
        Resident resident = new Resident();
        resident.setName("New Resident");
        resident.setEmail("newresident@gmail.com");
        resident.setPassword("password");
        resident.setId("");

        assertEquals(resident.getName(), "New Resident");
    }
}
