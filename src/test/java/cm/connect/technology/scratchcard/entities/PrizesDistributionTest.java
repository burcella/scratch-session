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
class PrizesDistributionTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PrizesDistribution entity;
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

    PrizesDistribution prizesDistribution = Instancio.create(PrizesDistribution.class);
    PrizesDistribution prizesDistribution1 = Instancio.create(PrizesDistribution.class);

    @Test
    void getId() {
        PrizesDistribution myEntity = new PrizesDistribution();
        myEntity.setId(10L);
        assertEquals(10L, myEntity.getId());
        assertNotEquals(10L, prizesDistribution.getId());
    }

    @Test
    void getNumberOfTicket() {
        PrizesDistribution myEntity = new PrizesDistribution();
        myEntity.setNumberOfTicket(100L);
        assertEquals(100L, myEntity.getNumberOfTicket());
        assertNotEquals(100L, prizesDistribution.getNumberOfTicket());
    }

    @Test
    void getAmount() {
        PrizesDistribution myEntity = new PrizesDistribution();
        myEntity.setAmount(100.0);
        assertEquals(100.0, myEntity.getAmount());
        assertNotEquals(100.0, prizesDistribution.getAmount());
    }

    @Test
    void getSymbol() {
        PrizesDistribution myEntity = new PrizesDistribution();
        myEntity.setSymbol("symbol");
        assertEquals("symbol", myEntity.getSymbol());
        assertNotEquals("symbol", prizesDistribution.getSymbol());
    }

    @Test
    void canEqual() {
        assertEquals(prizesDistribution, prizesDistribution);
        assertNotEquals(prizesDistribution, prizesDistribution1);
        assertNotEquals(null, prizesDistribution);
        assertNotEquals(null, prizesDistribution);
    }

    @Test
    void testToString() {
        String print = prizesDistribution.toString();
        System.out.println(print);
    }

    @Test
    void builder() {
        PrizesDistribution myEntity = PrizesDistribution.builder()
                .amount(100.0)
                .symbol("USD")
                .numberOfTicket(5L)
                .build();

        assertEquals(100.0, myEntity.getAmount());
        assertEquals("USD", myEntity.getSymbol());
        assertEquals(5L, myEntity.getNumberOfTicket());
    }
}