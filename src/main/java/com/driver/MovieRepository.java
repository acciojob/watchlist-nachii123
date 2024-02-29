package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class MovieRepository extends Movie{

    private HashMap<String, Movie> movieMap;
    private HashMap<String, Director> directorMap;
    private HashMap<String, List<String>> directorMovieMapping;

    public MovieRepository(){
        this.movieMap = new HashMap<String, Movie>();
        this.directorMap = new HashMap<String, Director>();
        this.directorMovieMapping = new HashMap<String, List<String>>();
    }

    public void saveMovie(Movie movie){
        // your code here
         movieMap.put(movie.getName(),movie);
    }

    public void saveDirector(Director director){
        // your code here
        directorMap.put(director.getName(),director);
    }

    public void saveMovieDirectorPair(String movie, String director){
        if(movieMap.containsKey(movie) && directorMap.containsKey(director)){
            // your code here
            // take list if moives present or not
            movieMap.put(movie,movieMap.get(movie));
            directorMap.put(director,directorMap.get(director));
           List<String> movies = directorMovieMapping.getOrDefault(director, new ArrayList<>());
           movies.add(movie);
           directorMovieMapping.put(director,movies);

        }
    }

    public Movie findMovie(String movie){
        // your code here
        return movieMap.get(movie);

    }

    public Director findDirector(String director){
        // your code here
        return directorMap.get(director);
    }

    public List<String> findMoviesFromDirector(String director){
        // your code here
        return directorMovieMapping.getOrDefault(director,new ArrayList<>());
    }

    public List<String> findAllMovies(){
        return new ArrayList<>(movieMap.keySet());
    }

    public void deleteDirector(String director){
        // your code here
        //Remove director from directorMap
        if(directorMovieMapping.containsKey(director)){
            List<String> movies = directorMovieMapping.get(director);

            for(String movie: movies){
                if(movieMap.containsKey(movie)){
                    movieMap.remove(movie);
                }

            }



            //Remove director from directorMovieMapping
            directorMovieMapping.remove(director);

        }
        if(directorMap.containsKey(director)){
            directorMap.remove(director);
        }


    }

//    public void deleteAllDirector(){
//        // your code here
//        //clear all director from directorMap
//        directorMap.clear();
//        movieMap.clear();
//
//        //clear alll director from directorMovieMapping
//        directorMovieMapping.clear();
//    }
public void deleteAllDirector(){
    HashSet<String> moviesSet = new HashSet<String>();

    for(String director: directorMovieMapping.keySet()){
        for(String movie: directorMovieMapping.get(director)){
            moviesSet.add(movie);
        }
    }
    for(String movie: moviesSet){
        if(movieMap.containsKey(movie)){
            movieMap.remove(movie);
        }
    }
}
      //
}