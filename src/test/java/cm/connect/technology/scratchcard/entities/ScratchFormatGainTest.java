package cm.connect.technology.scratchcard.entities;

import cm.connect.technology.scratchcard.ScratchCardApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.instancio.Instancio;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest
@ContextConfiguration(classes = ScratchCardApplication.class)
class ScratchFormatGainTest {


    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ScratchFormatGain entity;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    @AfterEach
    void tearDown() {
    }


    ScratchFormatGain scratchFormatGain = Instancio.create(ScratchFormatGain.class);
    ScratchFormatGain scratchFormatGain1 = Instancio.create(ScratchFormatGain.class);

    @Test
    public void entityInitializedCorrectly() {
        Assertions.assertThat(entity).isNotNull();
    }
    @Test
    void getName() {
        ScratchFormatGain myEntity = new ScratchFormatGain();
        myEntity.setName("name");
        assertEquals("name", myEntity.getName());
        assertNotEquals("name", scratchFormatGain.getName());
    }

    @Test
    void getRepeatingNumber() {
        ScratchFormatGain myEntity = new ScratchFormatGain();
        myEntity.setRepeatingNumber(14);
        assertEquals(14, myEntity.getRepeatingNumber());
        assertNotEquals(14, scratchFormatGain.getRepeatingNumber());
    }

    @Test
    void getTotalNumber() {
        ScratchFormatGain myEntity = new ScratchFormatGain();
        myEntity.setTotalNumber(200000);
        assertEquals(200000, myEntity.getTotalNumber());
        assertNotEquals(200000, scratchFormatGain.getTotalNumber());
    }

    @Test
    void canEqual() {
        assertEquals(scratchFormatGain, scratchFormatGain);
        assertNotEquals(scratchFormatGain, scratchFormatGain1);
        assertNotEquals(null, scratchFormatGain);
        assertNotEquals(null, scratchFormatGain);
    }

    @Test
    void testHashCode() {
    }

    @Test
    void testToString() {
        String print = scratchFormatGain.toString();
        System.out.println(print);
    }


    @Test
    void builder() {
        ScratchFormatGain myEntity = ScratchFormatGain.builder()
                .repeatingNumber(3)
                .name("USD")
                .totalNumber(10000000)
                .build();

        assertEquals(3, myEntity.getRepeatingNumber());
        assertEquals("USD", myEntity.getName());
        assertEquals(10000000, myEntity.getTotalNumber());
    }
}