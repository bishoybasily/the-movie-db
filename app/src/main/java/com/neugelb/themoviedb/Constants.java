package com.neugelb.themoviedb;

public interface Constants {

    interface Cache {

        String
                HTTP_DIR = "http-cache";

        Long
                HTTP_SIZE = (long) (50 * 1024 * 1024);

    }

    interface API {

        Long
                CONNECTION_TIMEOUT_SECONDS = 30L;

        String
                BASE_URL = "https://api.themoviedb.org/3/";

        interface Endpoints {

            String

                    MOVIES = "movie/",
                    MOVIES_POPULAR = MOVIES + "popular/",
                    MOVIES_LATEST = MOVIES + "latest/",
                    MOVIES_TOP_RATED = MOVIES + "top_rated/",
                    MOVIES_UPCOMING = MOVIES + "upcoming/",
                    MOVIES_NOW_PLAYING = MOVIES + "now_playing/",
                    MOVIE = MOVIES + "{id}/",
                    MOVIE_IMAGES = MOVIE + "images/",
                    MOVIE_CREDITS = MOVIE + "credits/",
                    MOVIE_VIDEOS = MOVIE + "videos/";


        }


    }


    //https://api.themoviedb.org/3/movies/popular/?page=1&api_key=08bb0bd7b85af9ed9b38f6f2161512b3
    //https://api.themoviedb.org/3/movie/popular/?page=1&api_key=08bb0bd7b85af9ed9b38f6f2161512b3

    interface Extra {

        String
                SOURCE = "source",
                MOVIE = "movie";

    }

    interface Action {


    }

    interface Configuration {

        Boolean
                TESTING = false,
                LOGGING = true,
                EMULATOR = true;

    }

}
