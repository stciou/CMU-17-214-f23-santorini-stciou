package edu.cmu.cs214.hw3;

public class MoveRequest {
        private int x;
        private int y;
        private String player;

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public String getPlayer() {
            return player;
        }

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }   

        public void setPlayer(String player) {
            this.player = player;
        }

        public MoveRequest(int x, int y, String player) {
            this.x = x;
            this.y = y;
            this.player = player;
        }
    }
