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
class ScratchCardTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ScratchCard entity;
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

    @Test
    public void entityInitializedCorrectly() {
        Assertions.assertThat(entity).isNotNull();
    }

    ScratchCard scratchCard = Instancio.create(ScratchCard.class);
    ScratchCard scratchCard1 = Instancio.create(ScratchCard.class);

    @Test
    void getId() {
        ScratchCard myEntity = new ScratchCard();
        myEntity.setId(10L);
        assertEquals(10L, myEntity.getId());
        assertNotEquals(10L, scratchCard.getId());
    }

    @Test
    void getGameName() {
        ScratchCard myEntity = new ScratchCard();
        myEntity.setGameName("game");
        assertEquals("game", myEntity.getGameName());
        assertNotEquals("game", scratchCard.getGameName());
    }

    @Test
    void getBoxValue() {
        ScratchCard myEntity = new ScratchCard();
        myEntity.setBoxValue("1000, 5000, 10k");
        assertEquals("1000, 5000, 10k", myEntity.getBoxValue());
        assertNotEquals("1000, 5000, 10k", scratchCard.getBoxValue());
    }

    @Test
    void getPrizeAmount() {
        ScratchCard myEntity = new ScratchCard();
        myEntity.setPrizeAmount(1000.0);
        assertEquals(1000.0, myEntity.getPrizeAmount());
        assertNotEquals(1000.0, scratchCard.getPrizeAmount());
    }

    @Test
    void getPoolsNumber() {
        ScratchCard myEntity = new ScratchCard();
        myEntity.setPoolsNumber(10);
        assertEquals(10, myEntity.getPoolsNumber());
        assertNotEquals(10, scratchCard.getPoolsNumber());
    }

    @Test
    void getFormat() {
    }

    @Test
    void getTimestamp() {
    }

    @Test
    void getIsScratched() {
    }

    @Test
    void getIsWinning() {
    }

    @Test
    void getTicket() {
    }

    @Test
    void setId() {
    }

    @Test
    void setGameName() {
    }

    @Test
    void setBoxValue() {
    }

    @Test
    void setPrizeAmount() {
    }

    @Test
    void setPoolsNumber() {
    }

    @Test
    void setFormat() {
    }

    @Test
    void setTimestamp() {
    }

    @Test
    void setIsScratched() {
    }

    @Test
    void setIsWinning() {
    }

    @Test
    void setTicket() {
    }

    @Test
    void testEquals() {
    }

    @Test
    void canEqual() {
    }

    @Test
    void testHashCode() {
    }

    @Test
    void testToString() {
    }

    @Test
    void builder() {
    }
}