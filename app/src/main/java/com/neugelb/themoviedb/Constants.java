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
                BASE_API_URL = "https://api.themoviedb.org/3/",
                BASE_MEDIA_URL = "https://image.tmdb.org/t/p/w500/";

        interface Endpoints {

            String

                    SEARCH = "search/",
                    SEARCH_MOVIES = SEARCH + "movie/",
                    MOVIES = "movie/",
                    MOVIES_POPULAR = MOVIES + "popular/",
                    MOVIES_TOP_RATED = MOVIES + "top_rated/",
                    MOVIES_UPCOMING = MOVIES + "upcoming/",
                    MOVIES_NOW_PLAYING = MOVIES + "now_playing/",
                    MOVIE = MOVIES + "{id}/",
                    MOVIE_IMAGES = MOVIE + "images/",
                    MOVIE_CREDITS = MOVIE + "credits/",
                    MOVIE_VIDEOS = MOVIE + "videos/";


        }


    }

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
