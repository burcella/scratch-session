package cm.connect.technology.scratchcard.Web.Impl;

import cm.connect.technology.scratchcard.ScratchCardApplication;
import cm.connect.technology.scratchcard.dto.ResponseDto;
import cm.connect.technology.scratchcard.entities.ScratchFormatGain;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.instancio.Instancio;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@WebMvcTest
@ContextConfiguration(classes = ScratchCardApplication.class)
class ScratchFormatGainRestControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ScratchFormatGainRestControllerImpl controller;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void controllerInitializedCorrectly() {
        Assertions.assertThat(controller).isNotNull();
    }


    @AfterEach
    void tearDown() {
    }

    @Test
    void getAll() throws Exception {
      ResponseDto<List<ScratchFormatGain>> listDto = new ResponseDto<>();
       ScratchFormatGain entity = Instancio.create(ScratchFormatGain.class);
        ScratchFormatGain entity1 = Instancio.create(ScratchFormatGain.class);
        ScratchFormatGain entity2 = Instancio.create(ScratchFormatGain.class);

        List<ScratchFormatGain> list = new ArrayList<>(Arrays.asList(entity, entity1, entity2));
        listDto.setBody(list);

        Mockito.when(controller.getAll(any(Locale.class))).thenReturn(listDto);

        ResultActions response = mockMvc.perform(get("/api/scratchFormatGain/getAll")
                .header("Accept-Language", false)
                .contentType( "application/json")
                .content(objectMapper.writeValueAsString(listDto)));

        response.andDo(print())
               .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.body", hasSize(3)))
                .andExpect(jsonPath("$.body[0].name",
                       is(entity.getName())))
                .andExpect(jsonPath("$.body[1].name",
                       is(entity1.getName())))
                .andExpect(jsonPath("$.body[2].name",
                       is(entity2.getName())))
                .andExpect(jsonPath("$.body[0].repeatingNumber",
                       is(entity.getRepeatingNumber())))
                .andExpect(jsonPath("$.body[1].repeatingNumber",
                       is(entity1.getRepeatingNumber())))
                .andExpect(jsonPath("$.body[2].repeatingNumber",
                        is(entity2.getRepeatingNumber())))
                .andExpect(jsonPath("$.body[0].totalNumber",
                       is(entity.getTotalNumber())))
                .andExpect(jsonPath("$.body[1].totalNumber",
                       is(entity1.getTotalNumber())))
                .andExpect(jsonPath("$.body[2].totalNumber",
                       is(entity2.getTotalNumber())));
    }

    @Test
    void create() throws Exception {
        ResponseDto<ScratchFormatGain> scratchFormatGainResponseDto = new ResponseDto<>();
        ScratchFormatGain scratchFormatGain = Instancio.create(ScratchFormatGain.class);
        scratchFormatGainResponseDto.setBody(scratchFormatGain);

        given(controller.create(any(Locale.class), any(ScratchFormatGain.class)))
                .willReturn(scratchFormatGainResponseDto);

        ResultActions response = mockMvc.perform(post("/api/scratchFormatGain/create")
                .header("Accept-Language", false)
                .contentType( "application/json")
                .content(objectMapper.writeValueAsString(scratchFormatGainResponseDto)));

        response.andDo(print())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.body.name",
                        is(scratchFormatGain.getName())))
                .andExpect(jsonPath("$.body.repeatingNumber",
                        is(scratchFormatGain.getRepeatingNumber())))
                .andExpect(jsonPath("$.body.totalNumber",
                is(scratchFormatGain.getTotalNumber())));
    }

    @Test
    void getByName() throws Exception {
        ResponseDto<ScratchFormatGain> scratchFormatGainResponseDto = new ResponseDto<>();
        ScratchFormatGain scratchFormatGain = Instancio.create(ScratchFormatGain.class);
        scratchFormatGain.setName("scratchGame");
        scratchFormatGainResponseDto.setBody(scratchFormatGain);

        given(controller.getByName (any(Locale.class), any(String.class)))
                .willReturn(scratchFormatGainResponseDto);

        ResultActions response = mockMvc.perform(get("/api/scratchFormatGain/name/{name}", "scratchGame")
                .header("Accept-Language", false)
                .contentType( "application/json" )
                .content(objectMapper.writeValueAsString(scratchFormatGainResponseDto)));

        response.andDo(print())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.body.name",
                        is(scratchFormatGain.getName())))
                .andExpect(jsonPath("$.body.repeatingNumber",
                        is(scratchFormatGain.getRepeatingNumber())))
                .andExpect(jsonPath("$.body.totalNumber",
                        is(scratchFormatGain.getTotalNumber())));
    }

    @Test
    void delete() {
    }
}