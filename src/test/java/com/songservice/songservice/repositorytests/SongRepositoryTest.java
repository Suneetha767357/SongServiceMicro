package com.songservice.songservice.repositorytests;

import com.songservice.songservice.models.Song;
import com.songservice.songservice.repository.SongRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import testutils.TestSongs;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataMongoTest
public class SongRepositoryTest {


    @Autowired
    private SongRepository songRepository;

    private List<Song> testSongs;

    @Before
    public void setUp() {
        testSongs = new ArrayList<Song>();

        for(Song song :  TestSongs.getSongs()) {
            Song songWithid = songRepository.save(song);
        }
    }


    @Test
    public void repoSearchesByArist() throws Exception {
        List<Song> songByArtist = songRepository.findByArtist("The Chainsmokers");

        for(Song song : songByArtist)
            assertThat(song.getArtist()).isEqualTo("The Chainsmokers");
    }


}
