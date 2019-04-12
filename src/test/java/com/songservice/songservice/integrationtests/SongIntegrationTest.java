package com.songservice.songservice.integrationtests;

import com.songservice.songservice.models.Song;
import com.songservice.songservice.repository.SongRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import testutils.TestSongs;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class SongIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    SongRepository songRepository;


    private List<Song> testSongs;


    @Before
    public void setUp() {
        testSongs = new ArrayList<Song>();

        for(Song song :  TestSongs.getSongs()) {
            ResponseEntity<Song> songResponseEntity = testRestTemplate.postForEntity("/song" , song , Song.class);
            testSongs.add(songResponseEntity.getBody());
        }
    }

    @Test
    public void postingSong_savesTheSong(){
        //act
        ResponseEntity<Song> response = testRestTemplate.postForEntity(
                "/song", TestSongs.getSongs().get(1),Song.class
        );

        Song song = response.getBody();

        //assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
      //  assertThat(response.getBody()).isEqualTo("saved");

        assertThat(song.getId()).isNotNull();

        // tear down
        songRepository.delete(song);
    }

//    @Test
//    public void listSong_retrievesTheSongs() {
//
//        // act
//        ResponseEntity<List<Song>> response = testRestTemplate.exchange(
//                "/songs",
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<Song>>(){});
//
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//    }
//
//
    @Test
    public void searchForSongByName_returnsSongsWithMatchingNames() throws Exception {
        //arrange
        testRestTemplate.postForEntity("/song", TestSongs.getSongs().get(0),String.class);

        //act
        ResponseEntity<Song[]> response = testRestTemplate.getForEntity("/songs/africa", Song[].class);

        //assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        for (Song song : response.getBody()) {
            assertThat(song.getTitle()).contains("Africa");
        }
    }

    @Test
    public void searchForSongByArtist_returnsSongsByThatArtist() throws Exception {
        //arrange

        //act
        ResponseEntity<Song[]> response = testRestTemplate.getForEntity("/songs/artist/The Chainsmokers", Song[].class);

        //assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        for (Song song : response.getBody()) {
            assertThat(song.getArtist()).contains("The Chainsmokers");
        }
    }




}




