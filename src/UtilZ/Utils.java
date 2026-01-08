package UtilZ;

public class Utils {
    public static class Directions
    {
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }
    public static class PlayerConstants{
        public static final int IDLE = 0;
        public static final int MOVING = 2;
        public static final int JUMP = 1;
        public static final int ATACK = 5;
        public static final int ATACKJUMP = 4;
        public static final int DOWN = 6;

        public static int getFrames(int state)
        {
            switch (state)
            {
                case JUMP:
                    return 3;
                case IDLE:
                    return 4;
                case MOVING:
                    return 4;
                case ATACK:
                    return 4;
                case ATACKJUMP:
                    return 4;
                case DOWN:
                    return 2;
                default:
                    return 1;
            }
        }

        public static int getStatus(int state)
        {
            switch (state)
            {
                case IDLE:
                    return 0;
                case MOVING:
                    return 2;
                case JUMP:
                    return 1;
                case ATACK:
                    return 5;
                case ATACKJUMP:
                    return 4;
                case DOWN:
                    return 6;
                default:
                    return 0;
            }
        }
    }
}
