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
                BASE_URL = "https://api.themoviedb.org/3";

        interface Endpoints {

            String

                    MOVIE = "/movie/{id}",
                    MOVIES_IMAGES = MOVIE + "/images",
                    MOVIES_CREDITS = MOVIE + "/credits",
                    MOVIES_VIDEOS = MOVIE + "/videos",
                    MOVIES = "/movies",
                    MOVIES_POPULAR = MOVIES + "/popular",
                    MOVIES_LATEST = MOVIES + "/latest",
                    MOVIES_TOP_RATED = MOVIES + "/top_rated",
                    MOVIES_UPCOMING = MOVIES + "/upcoming",
                    MOVIES_NOW_PLAYING = MOVIES + "/now_playing";


        }


    }


    interface Extra {


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
