package com.songservice.songservice.service;

import com.songservice.songservice.models.Song;
import com.songservice.songservice.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {


    private SongRepository repository;


    @Autowired
    public SongService(SongRepository repository) {
        this.repository = repository;
    }

    public Song saveSong(Song song) {

      Song s =   repository.save(song);
      return s;
    }

    public List<Song> retrieveSongs() {
        return repository.findAll();
    }




    public List<Song> retrieveSongsByTitle(String title) {
        return repository.findByTitle(title);
    }

    public List<Song> getSongByArtist(String artist) {
        return repository.findByArtist(artist);
    }


}
