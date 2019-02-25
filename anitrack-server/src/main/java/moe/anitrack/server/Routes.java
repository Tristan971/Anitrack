package moe.anitrack.server;

public final class Routes {

    public static final String PLAYER = "/player";

    private Routes() {
    }

    public static final class Player {

        public static final String INFO = "/info";
        public static final String PLAYED = "/played";

        private Player() {
        }

    }

}
