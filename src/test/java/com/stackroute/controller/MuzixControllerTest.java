package com.stackroute.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.domain.Muzix;
import com.stackroute.service.MuzixService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
public class MuzixControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private MuzixService muzixService;

    private Muzix muzix;
    @InjectMocks
    private MuzixController muzixController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(muzixController).build();

    }
    //converts json to String object
    public String jsonToString(final Object object)
    {
        String string="";
        try {
            string=new ObjectMapper().writeValueAsString(object);
        } catch( JsonProcessingException e) {
            e.printStackTrace();
        }
        return string;
    }

    @Test   //Tests saveMuzix()
    public void testSaveTrack() throws Exception {
        Muzix muzix = new Muzix(1, "Track1", "Good");
        when(muzixService.saveTrack(muzix)).thenReturn(muzix);
        mockMvc.perform(post("/api/muzix")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON)
              .content(jsonToString(muzix)))
                .andExpect(status().isCreated());
        verify(muzixService, times(1)).saveTrack(Mockito.any(Muzix.class));
        verifyNoMoreInteractions(muzixService);
    }


    @Test   //Tests deleteMuzix()
    public void testDeleteTrack() throws Exception {
        Muzix muzix1 = new Muzix(1, "Track1", "Good");
        List<Muzix> list=new ArrayList<>();
        when(muzixService.deleteTrack(muzix1.getTrackId())).thenReturn(list);
        mockMvc.perform(delete("/api/muzix")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonToString(muzix1)))
                .andExpect(status().isOk());
    }

    @Test       //tests getAllMuzix
    public void testGetAllTracks() throws Exception {
        Muzix muzix1 = new Muzix(1, "Track1", "Good");
        List<Muzix> list=new ArrayList<>();
        list.add(muzix1);
        when(muzixService.getAllTracks()).thenReturn(list);
        mockMvc.perform(get("/api/muzix")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonToString(muzix1)))
                .andExpect(status().isOk());

    }
@Test       //tests updateMuzix
    public void testUpdateTracks() throws Exception{
    Muzix muzix = new Muzix(1, "Track1", "Good");
        when(muzixService.updateTrack(muzix,muzix.getTrackId())).thenReturn(muzix);
    mockMvc.perform(put("/api/muzix")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .accept(MediaType.APPLICATION_JSON)
            .content(jsonToString(muzix)))
            .andExpect(status().isOk());
}

}
