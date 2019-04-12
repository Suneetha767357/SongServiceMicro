package com.songservice.songservice.controllertests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.songservice.songservice.controller.SongController;
import com.songservice.songservice.models.Song;
import com.songservice.songservice.service.SongService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import testutils.TestSongs;


import java.util.ArrayList;
import java.util.List;


import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
@WebMvcTest(SongController.class)
public class SongControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SongService songService;


    @Test
    public void findSongByArtist_returnsSongsByThatArtist() throws Exception {

        List<Song> songs = new ArrayList<Song>();
        songs.add(TestSongs.getSongs().get(5));
        songs.add(TestSongs.getSongs().get(6));


        when(songService.getSongByArtist("The Chainsmokers")).thenReturn(songs);


        mockMvc.perform(MockMvcRequestBuilders.get("/songs/artist/The Chainsmokers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].artist", is("The Chainsmokers")))
                .andExpect(jsonPath("$[1].artist", is("The Chainsmokers")));

        verify(songService, times(1)).getSongByArtist("The Chainsmokers");
        verifyNoMoreInteractions(songService);

    }
}
//
//
//}
