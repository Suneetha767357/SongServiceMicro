package com.songservice.songservice.servicestests;

import com.songservice.songservice.models.Song;
import com.songservice.songservice.repository.SongRepository;
import com.songservice.songservice.service.SongService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SongServiceTest {


    @Mock
    private SongRepository songRepository;

    private SongService songService;

    @Before
    public void init() {
        songService = new SongService(songRepository);
    }



    @Test
    public void getSongsByArtist_returnsSongByThatArtist() {

        // ACT
        List<Song> songs = songService.getSongByArtist("The Chainsmokers");


        verify(songRepository , times(1)).findByArtist(any(String.class));

    }
}
